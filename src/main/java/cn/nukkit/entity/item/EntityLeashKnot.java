package cn.nukkit.entity.item;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.item.Item;
import cn.nukkit.level.Sound;
import cn.nukkit.level.format.IChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import org.jetbrains.annotations.NotNull;

public class EntityLeashKnot extends Entity {
    public EntityLeashKnot(IChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    public @NotNull String getIdentifier() {
        return LEASH_KNOT;
    }

    @Override
    public float getWidth() {
        return 0.4f; // TODO: Correct value
    }

    @Override
    public float getHeight() {
        return 0.8f; // TODO: Correct value
    }

    @Override
    public void initEntity() {
        super.initEntity();
        this.level.addSound(this, Sound.LEASHKNOT_PLACE, 1.0F, 1.0F);
    }

    @Override
    public boolean attack(EntityDamageEvent source) {
        DamageCause cause = source.getCause(); // TODO

        this.close(true);
        return true;
    }

    @Override
    public boolean onInteract(Player player, Item item) {
        this.close(true);
        return true;
    }

    public void close(boolean playsound) {
        if (playsound) {
            this.level.addSound(this, Sound.LEASHKNOT_BREAK, 1.0F, 1.0F);
        }

        super.close();
    }
}