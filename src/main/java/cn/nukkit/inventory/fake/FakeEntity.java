package cn.nukkit.inventory.fake;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityID;
import cn.nukkit.entity.custom.CustomEntity;
import cn.nukkit.entity.passive.EntityVillagerV2;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.AddEntityPacket;
import cn.nukkit.network.protocol.BlockEntityDataPacket;
import cn.nukkit.network.protocol.EntityEventPacket;
import cn.nukkit.network.protocol.RemoveEntityPacket;
import cn.nukkit.network.protocol.UpdateBlockPacket;
import cn.nukkit.network.protocol.types.EntityLink;
import cn.nukkit.registry.Registries;

import java.util.HashSet;


public class FakeEntity implements FakeBlock {
    protected final String eN;
    protected final String tileId;
    protected HashSet<Vector3> lastPositions = new HashSet<>();
    protected final long eId;

    public FakeEntity(String eId) {
        this.eN = eId;
        this.tileId = "default";
        this.eId = Entity.entityCount.getAndIncrement();
    }

    public long getId() {
        return eId;
    }

    @Override
    public void create(Player player) {
        create(player, "default");
    }

    @Override
    public void create(Player player, String titleName) {
        lastPositions.addAll(this.getPlacePositions(player));
        lastPositions.forEach(position -> {
            AddEntityPacket addEntity = new AddEntityPacket();
            addEntity.type = Registries.ENTITY.getEntityNetworkId(EntityID.VILLAGER_V2);
            addEntity.entityUniqueId = this.getId();
            addEntity.entityRuntimeId = this.getId();
            addEntity.yaw = 0f;
            addEntity.headYaw = 0f;
            addEntity.pitch = 0f;
            addEntity.x = (float) position.x;
            addEntity.y = (float) position.y;
            addEntity.z = (float) position.z;
            addEntity.speedX = 0f;
            addEntity.speedY = 0f;
            addEntity.speedZ = 0f;

            player.dataPacket(addEntity);
        });
    }

    @Override
    public void remove(Player player) {
        this.lastPositions.forEach(position -> {
            RemoveEntityPacket pk = new RemoveEntityPacket();
            pk.eid = this.getId();
            player.dataPacket(pk);
        });
        lastPositions.clear();
    }

    public HashSet<Vector3> getLastPositions() {
        return lastPositions;
    }
}
