package cn.nukkit.entity.item;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityCreature;
import cn.nukkit.entity.data.EntityFlag;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.level.ParticleEffect;
import cn.nukkit.level.Sound;
import cn.nukkit.level.format.IChunk;
import cn.nukkit.math.NukkitMath;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

public class EntityBalloon extends Entity {

    protected Entity balloonAttached;
    protected float balloonMaxHeight;
    protected boolean balloonShouldDrop;
    public int color = 0;

    @Override
    @NotNull
    public String getIdentifier() {
        return BALLOON;
    }

    public EntityBalloon(IChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public void initEntity() {
        super.initEntity();

        this.setMaxHealth(1);
        this.setHealth(1);

        if (!this.namedTag.contains("Color")) {
            this.setColor(0);
        } else {
            this.setColor(this.namedTag.getByte("Color"));
        }

        this.setDataFlag(EntityFlag.HAS_GRAVITY, false);

        if (this.namedTag.contains("balloon_attached")) {
            this.balloonAttached = this.level.getEntity(this.namedTag.getLong("balloon_attached"));
            if (this.balloonAttached != null) {
                this.setDataProperty(BALLOON_ANCHOR_EID, this.balloonAttached.getId()); // TODO: Or DATA_LEAD_HOLDER_EID?
                this.setDataFlag(EntityFlag.LEASHED, true);
            }
        } else {
            this.balloonAttached = null; // Not Attached
        }
        if (this.namedTag.contains("balloon_max_height")) {
            this.balloonMaxHeight = this.namedTag.getFloat("balloon_max_height");
        } else {
            this.balloonMaxHeight = 256f;
        }
        if (this.namedTag.contains("balloon_should_drop")) {
            this.balloonShouldDrop = this.namedTag.getBoolean("balloon_should_drop");
        } else {
            balloonShouldDrop = false;
        }
    }

    @Override
    public @NotNull String getName() {
        return "Balloon";
    }

    @Override
    public float getWidth() {
        return 0.4f;
    }

    @Override
    public float getHeight() {
        return 0.4f;
    }

    @Override
    protected float getGravity() {
        return -.1f; // Should be Lift Force, balloon doesn't have gravity
    }

    @Override
    protected float getDrag() {
        return 0.02f; // TODO: Correct value
    }

    @Override
    public boolean attack(EntityDamageEvent source) {
        DamageCause cause = source.getCause();
        // TODO: Add Functionality
        if (source instanceof EntityDamageByEntityEvent) {
            Entity damager = ((EntityDamageByEntityEvent) source).getDamager();

            double deltaX = this.x - damager.x;
            double deltaZ = this.z - damager.z;
            this.knockBack(damager, source.getDamage(), deltaX, deltaZ, 0.1);
        }
        return true;
    }

    @Override
    public void saveNBT() {
        super.saveNBT();

        this.namedTag.putByte("Color", this.color);
        this.namedTag.putLong("balloon_attached", (balloonAttached != null ? balloonAttached.getId() : -1L));
        this.namedTag.putFloat("balloon_max_height", balloonMaxHeight);
        this.namedTag.putBoolean("balloon_should_drop", balloonShouldDrop);
    }

    @Override
    public boolean onUpdate(int currentTick) {
        if (this.closed) {
            return false;
        }

        int tickDiff = currentTick - this.lastUpdate;
        if (tickDiff <= 0 && !this.justCreated) {
            return true;
        }

        this.lastUpdate = currentTick;

        boolean hasUpdate = this.entityBaseTick(tickDiff);

        if (this.isAlive()) {
            if (this.y >= this.balloonMaxHeight) {
                if (isLeashed() && this.y < 256.0F) {
                    return true;
                }

                this.close();
                return false;
            }

            //move balloon a bit up (1 block max) and down to simulate floating if attached
            if (this.isAttached()) {
                //if distance is greater than 1 block, move down
                if (this.distance(this.balloonAttached) > 5) {
                    this.move(0, -0.1, 0);
                } else if (this.distance(this.balloonAttached) < 4) {
                    //move up if distance is less than 1 block
                    this.move(0, 0.1, 0);
                }
            } else {
                //move up if not attached
                this.move(0, 0.1, 0);
            }
            

            updateMovement();
            if (this.getLevelBlock().up().isSolid() && !this.getLevelBlock().up().canPassThrough()) {
                this.close();
                this.level.addSound(this, Sound.BUBBLE_POP, 0.25F, (float) NukkitMath.round(ThreadLocalRandom.current().nextDouble(1.75D, 2.0D), 2));
                this.level.addParticleEffect(this, ParticleEffect.ENDROD);
            }
        }

        return hasUpdate || !this.onGround || Math.abs(this.motionX) > 0.00001 || Math.abs(this.motionY) > 0.00001 || Math.abs(this.motionZ) > 0.00001;
    }

    public Entity getAttachedEntity() {
        return this.balloonAttached;
    }

    public boolean isAttached() {
        return this.balloonAttached != null && !this.balloonAttached.isClosed();
    }

    public void knockBack(Entity attacker, double damage, double x, double z, double base) {
        double f = Math.sqrt(x * x + z * z);
        if (f <= 0) {
            return;
        }

        f = 1 / f;

        Vector3 motion = new Vector3(this.motionX, this.motionY, this.motionZ);

        motion.x /= 2d;
        motion.z /= 2d;
        motion.x += x * f * base;
        motion.z += z * f * base;

        this.setMotion(motion);
    }

    public boolean isLeashed() {
        return this.balloonAttached instanceof EntityLeashKnot;
    }

    @Override
    public void close() {
        super.close();

        if (this.balloonAttached != null) {
            this.balloonAttached.close();
        }
    }

    public int getColor() {
        return namedTag.getByte("Color");
    }

    public void setColor(int color) {
        this.color = color;
        this.setDataProperty(COLOR, color);
        this.namedTag.putByte("Color", this.color);
    }
}