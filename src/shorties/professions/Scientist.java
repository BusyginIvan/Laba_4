package shorties.professions;

import shorties.Profession;
import shorties.Shorty;

import java.util.Objects;

public class Scientist extends Profession implements ScientistInterface {
    public static Scientist extract(Shorty shorty) {
        return (Scientist) (shorty.getProfession(professionName));
    }

    private int iq;

    public Scientist(int iq) {
        super();
        this.iq = iq;
    }

    @Override
    public String getProfessionName() {
        return professionName;
    }

    /*public void suggestNumberOfSeats(int n) {
        System.out.println(getName() + " предложил" + (isMan() ? "" : "а") +" рассчитать корабль на " + n + " коротышек.");
    }*/

    /* get-теры и set-теры */

    public int getIQ() {
        return iq;
    }

    public boolean setIQ(int iq) {
        if (iq < 120)
            return false;
        this.iq = iq;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scientist scientist = (Scientist) o;
        return iq == scientist.iq && Objects.equals(getShorty(), scientist.getShorty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(iq);
    }

    @Override
    public String toString() {
        return "Scientist{" +
                "iq=" + iq +
                ", shorty=" + getShorty() +
                '}';
    }
}