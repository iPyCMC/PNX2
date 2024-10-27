package cn.nukkit.positiontracking;

import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * @author joserobjr
 */


public class PositionTracking extends NamedPosition {

    private @NotNull String levelName;

    public PositionTracking(@NotNull String levelName, double x, double y, double z) {
        super(x, y, z);
        this.levelName = levelName;
    }

    public PositionTracking(@NotNull Level level, double x, double y, double z) {
        this(level.getName(), x, y, z);
    }

    public PositionTracking(@NotNull Level level, Vector3 v) {
        this(level, v.x, v.y, v.z);
    }

    public PositionTracking(@NotNull String levelName, Vector3 v) {
        this(levelName, v.x, v.y, v.z);
    }

    public PositionTracking(@NotNull Position pos) {
        this(pos.getLevel(), pos.x, pos.y, pos.z);
    }

    public PositionTracking(@NotNull NamedPosition pos) {
        this(pos.getLevelName(), pos.x, pos.y, pos.z);
    }

    @Override
    @NotNull public String getLevelName() {
        return levelName;
    }

    public void setLevelName(@NotNull String levelName) {
        this.levelName = levelName;
    }

    @Override
    @NotNull public PositionTracking add(double x) {
        return add(x, 0, 0);
    }

    @Override
    public PositionTracking add(double x, double y) {
        return add(x, y, 0);
    }

    @Override
    public PositionTracking add(double x, double y, double z) {
        return new PositionTracking(levelName, this.x + x, this.y + y, this.z + z);
    }

    @Override
    public PositionTracking add(Vector3 v) {
        return new PositionTracking(levelName, x + v.x, y + v.y, z + v.z);
    }

    @Override
    public PositionTracking subtract(double x) {
        return subtract(x, 0, 0);
    }

    @Override
    public PositionTracking subtract(double x, double y) {
        return subtract(x, y, 0);
    }

    @Override
    public PositionTracking subtract(double x, double y, double z) {
        return add(-x, -y, -z);
    }

    @Override
    public PositionTracking subtract(Vector3 v) {
        return add(-v.x, -v.y, -v.z);
    }

    @Override
    public PositionTracking multiply(double number) {
        return new PositionTracking(levelName, x * number, y * number, z * number);
    }

    @Override
    public PositionTracking divide(double number) {
        return new PositionTracking(levelName, x * number, y * number, z * number);
    }

    @Override
    public PositionTracking ceil() {
        return new PositionTracking(levelName, Math.ceil(x), Math.ceil(y), Math.ceil(z));
    }

    @Override
    public PositionTracking floor() {
        return new PositionTracking(levelName, Math.floor(x), Math.floor(y), Math.floor(z));
    }

    @Override
    public PositionTracking round() {
        return new PositionTracking(levelName, Math.round(this.x), Math.round(this.y), Math.round(this.z));
    }

    @Override
    public PositionTracking abs() {
        return new PositionTracking(levelName, Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    @Override
    public PositionTracking getSide(BlockFace face) {
        return getSide(face, 1);
    }

    @Override
    public PositionTracking getSide(BlockFace face, int step) {
        return new PositionTracking(levelName, x + face.getXOffset() * step, y + face.getYOffset() * step, z + face.getZOffset() * step);
    }

    @Override
    public PositionTracking up() {
        return up(1);
    }

    @Override
    public PositionTracking up(int step) {
        return getSide(BlockFace.UP, step);
    }

    @Override
    public PositionTracking down() {
        return down(1);
    }

    @Override
    public PositionTracking down(int step) {
        return getSide(BlockFace.DOWN, step);
    }

    @Override
    public PositionTracking north() {
        return north(1);
    }

    @Override
    public PositionTracking north(int step) {
        return getSide(BlockFace.NORTH, step);
    }

    @Override
    public PositionTracking south() {
        return south(1);
    }

    @Override
    public PositionTracking south(int step) {
        return getSide(BlockFace.SOUTH, step);
    }

    @Override
    public PositionTracking east() {
        return east(1);
    }

    @Override
    public PositionTracking east(int step) {
        return getSide(BlockFace.EAST, step);
    }

    @Override
    public PositionTracking west() {
        return west(1);
    }

    @Override
    public PositionTracking west(int step) {
        return getSide(BlockFace.WEST, step);
    }

    @Override
    public @Nullable PositionTracking getIntermediateWithXValue(@NotNull Vector3 v, double x) {
        Vector3 intermediateWithXValue = super.getIntermediateWithXValue(v, x);
        if (intermediateWithXValue == null) {
            return null;
        }
        return new PositionTracking(levelName, intermediateWithXValue);
    }

    @Override
    public @Nullable Vector3 getIntermediateWithYValue(@NotNull Vector3 v, double y) {
        Vector3 intermediateWithYValue = super.getIntermediateWithYValue(v, y);
        if (intermediateWithYValue == null) {
            return null;
        }
        return new PositionTracking(levelName, intermediateWithYValue);
    }

    @Override
    public @Nullable Vector3 getIntermediateWithZValue(@NotNull Vector3 v, double z) {
        Vector3 intermediateWithZValue = super.getIntermediateWithZValue(v, z);
        if (intermediateWithZValue == null) {
            return null;
        }
        return new PositionTracking(levelName, intermediateWithZValue);
    }

    @Override
    public @Nullable PositionTracking setComponents(double x, double y, double z) {
        super.setComponents(x, y, z);
        return this;
    }

    @Override
    @NotNull public PositionTracking setComponents(@NotNull Vector3 position) {
        super.setComponents(position);
        return this;
    }

    @Override
    public PositionTracking clone() {
        return (PositionTracking) super.clone();
    }
}
