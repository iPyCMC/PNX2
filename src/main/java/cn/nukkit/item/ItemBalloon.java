package cn.nukkit.item;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockFence;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityID;
import cn.nukkit.level.Level;
import cn.nukkit.level.format.IChunk;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.DyeColor;

public class ItemBalloon extends Item {
    public ItemBalloon() {
        this(0, 1);
    }

    public ItemBalloon(Integer meta) {
        this(meta, 1);
    }

    public ItemBalloon(Integer meta, int count) {
        super(BALLOON, meta, count, DyeColor.getByWoolData(meta).getName() + " Balloon");
    }

    @Override
    public int getMaxStackSize() {
        return 16;
    }

    public DyeColor getDyeColor() {
        return DyeColor.getByDyeData(meta);
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        IChunk chunk = level.getChunk((int) block.getX() >> 4, (int) block.getZ() >> 4);

        if (chunk == null) {
            return false;
        }

        if (!(target instanceof BlockFence)) {
            return false;
        }

        CompoundTag nbtLeashKnot = new CompoundTag()
                .putList("Pos", new ListTag<DoubleTag>()
                        .add(new DoubleTag(target.getX() + 0.5))
                        .add(new DoubleTag(target.getY() + 0.25))
                        .add(new DoubleTag(target.getZ() + 0.5)))
                .putList("Motion", new ListTag<DoubleTag>()
                        .add(new DoubleTag(0))
                        .add(new DoubleTag(0))
                        .add(new DoubleTag(0)))
                .putList("Rotation", new ListTag<FloatTag>()
                        .add(new FloatTag(0))
                        .add(new FloatTag(0)));

        Entity entityLeashKnot = Entity.createEntity(EntityID.LEASH_KNOT, chunk, nbtLeashKnot);

        CompoundTag nbtBalloon = new CompoundTag()
                .putList("Pos", new ListTag<DoubleTag>()
                        .add(new DoubleTag(target.getX() + 1.0))
                        .add(new DoubleTag(target.getY() + 1.75))
                        .add(new DoubleTag(target.getZ() + 0.5)))
                .putList("Motion", new ListTag<DoubleTag>()
                        .add(new DoubleTag(0))
                        .add(new DoubleTag(0))
                        .add(new DoubleTag(0)))
                .putList("Rotation", new ListTag<FloatTag>()
                        .add(new FloatTag(0))
                        .add(new FloatTag(0)))
                .putByte("Color", this.getDamage() & 0xf)
                .putLong("balloon_attached", entityLeashKnot.getId());

        Entity entityBalloon = Entity.createEntity(EntityID.BALLOON, chunk, nbtBalloon);

        if (!player.isCreative()) {
            player.getInventory().decreaseCount(player.getInventory().getHeldItemIndex());
        }
        entityLeashKnot.spawnToAll();
        entityBalloon.spawnToAll();
        return true;
    }
}