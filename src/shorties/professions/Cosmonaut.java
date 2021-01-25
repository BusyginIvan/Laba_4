package shorties.professions;

import shorties.Profession;

import java.util.Objects;

public class Cosmonaut extends Profession implements CosmonautInterface {
    public static final String professionName = "Космонавт";
    private double mass;
    //private Spaceship spaceship;

    public Cosmonaut(double mass) {
        super();
        this.mass = mass;
        //spaceship = null;
    }

    @Override
    public String getProfessionName() {
        return professionName;
    }

    public double getMass() {
        return mass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cosmonaut cosmonaut = (Cosmonaut) o;
        return Double.compare(cosmonaut.mass, mass) == 0  && Objects.equals(getShorty(), cosmonaut.getShorty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mass, getShorty());
    }

    @Override
    public String toString() {
        return "Cosmonaut{" +
                "mass=" + mass +
                "shorty=" + getShorty() +
                '}';
    }
}
