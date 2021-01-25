package shorties.professions;

import plan.drawings.interfaces.EditMultistageSpaceshipDrawing;
import shorties.Profession;
import shorties.Shorty;

import java.util.Objects;

public abstract class Engineer extends Profession implements EngineerInterface {
    public static Engineer extract(Shorty shorty) {
        return (Engineer) (shorty.getProfession(professionName));
    }

    public Engineer() {
        super();
    }

    @Override
    public String getProfessionName() {
        return professionName;
    }

    @Override
    public abstract void changeDrawing(EditMultistageSpaceshipDrawing drawing);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engineer engineer = (Engineer) o;
        return Objects.equals(getShorty(), engineer.getShorty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShorty());
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "shorty=" + getShorty() +
                '}';
    }
}