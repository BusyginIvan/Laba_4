package space.space_objects;

import java.util.Objects;

public class SpaceObject {
    public final SpaceObjectType TYPE;
    public final double MASS;
    public final double RADIUS;
    public final double ACCELERATION_OF_GRAVITY;
    public final boolean HAS_ATMOSPHERE;

    public SpaceObject(SpaceObjectType type, double mass, double radius, double accelerationOfGravity, boolean hasAtmosphere) {
        TYPE = type;
        ACCELERATION_OF_GRAVITY = accelerationOfGravity;
        RADIUS = radius;
        MASS = mass;
        HAS_ATMOSPHERE = hasAtmosphere;
    }

    public double secondSpaceSpeed() {
        return Math.sqrt(2 * ACCELERATION_OF_GRAVITY * RADIUS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceObject that = (SpaceObject) o;
        return Double.compare(that.MASS, MASS) == 0 &&
                Double.compare(that.RADIUS, RADIUS) == 0 &&
                Double.compare(that.ACCELERATION_OF_GRAVITY, ACCELERATION_OF_GRAVITY) == 0 &&
                HAS_ATMOSPHERE == that.HAS_ATMOSPHERE &&
                TYPE == that.TYPE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(TYPE, MASS, RADIUS, ACCELERATION_OF_GRAVITY, HAS_ATMOSPHERE);
    }

    @Override
    public String toString() {
        return "SpaceObject{" +
                "TYPE=" + TYPE +
                ", MASS=" + MASS +
                ", RADIUS=" + RADIUS +
                ", ACCELERATION_OF_GRAVITY=" + ACCELERATION_OF_GRAVITY +
                ", HAS_ATMOSPHERE=" + HAS_ATMOSPHERE +
                '}';
    }
}