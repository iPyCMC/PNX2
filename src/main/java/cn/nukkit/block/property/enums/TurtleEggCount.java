package cn.nukkit.block.property.enums;

/**
 * Automatically generated by {@code org.allaymc.codegen.VanillaBlockPropertyTypeGen} <br>
 * Allay Project <p>
 *
 * @author daoge_cmd
 */
public enum TurtleEggCount {
    ONE_EGG,
    TWO_EGG,
    THREE_EGG,
    FOUR_EGG;

    public TurtleEggCount before() {
        return TurtleEggCount.values()[this.ordinal() - 1];
    }

    public TurtleEggCount next() {
        return TurtleEggCount.values()[this.ordinal() + 1];
    }
}
