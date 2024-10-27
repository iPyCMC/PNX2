package cn.nukkit.item;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockRail;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityChestMinecart;
import cn.nukkit.level.Level;
import cn.nukkit.math.BlockFace;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.Rail;

public class ItemChestMinecart extends Item {
    public ItemChestMinecart() {
        this(0, 1);
    }

    public ItemChestMinecart(Integer meta) {
        this(meta, 1);
    }

    public ItemChestMinecart(Integer meta, int count) {
        super(CHEST_MINECART, meta, count, "Minecart with Chest");
    }

    @Override
    public boolean canBeActivated() {
        return true;
    }

    @Override
    public boolean onActivate(Level level, Player player, Block block, Block target, BlockFace face, double fx, double fy, double fz) {
        if (Rail.isRailBlock(target)) {
            Rail.Orientation type = ((BlockRail) target).getOrientation();
            double adjacent = 0.0D;
            if (type.isAscending()) {
                adjacent = 0.5D;
            }
            EntityChestMinecart minecart = (EntityChestMinecart) Entity.createEntity(Entity.CHEST_MINECART,
                    level.getChunk(target.getFloorX() >> 4, target.getFloorZ() >> 4), new CompoundTag()
                            .putList("Pos", new ListTag<>()
                                    .add(new DoubleTag(target.getX() + 0.5))
                                    .add(new DoubleTag(target.getY() + 0.0625D + adjacent))
                                    .add(new DoubleTag(target.getZ() + 0.5)))
                            .putList("Motion", new ListTag<>()
                                    .add(new DoubleTag(0))
                                    .add(new DoubleTag(0))
                                    .add(new DoubleTag(0)))
                            .putList("Rotation", new ListTag<>()
                                    .add(new FloatTag(0))
                                    .add(new FloatTag(0)))
            );

            if (minecart == null) {
                return false;
            }

            if (player.isAdventure() || player.isSurvival()) {
                Item item = player.getInventory().getItemInHand();
                item.setCount(item.getCount() - 1);
                player.getInventory().setItemInHand(item);
            }

            minecart.spawnToAll();
            return true;
        }
        return false;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}