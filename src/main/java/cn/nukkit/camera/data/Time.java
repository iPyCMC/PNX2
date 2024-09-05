package cn.nukkit.camera.data;

import cn.nukkit.nbt.tag.CompoundTag;

/**
 * @author daoge_cmd
 * @date 2023/6/11
 * PowerNukkitX Project
 */


public record Time(float fadeIn, float hold, float fadeOut) implements SerializableData {
    public CompoundTag serialize() {
        return new CompoundTag()//time
                .putFloat("fadeIn", fadeIn)
                .putFloat("hold", hold)
                .putFloat("fadeOut", fadeOut);
    }
}
