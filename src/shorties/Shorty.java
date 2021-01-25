package shorties;

import java.util.HashMap;
import java.util.Objects;

public class Shorty {
    public final String name;
    public final boolean isMan;
    HashMap<String, Profession> professions;

    public Shorty(String name, boolean isMan) {
        this.name = name;
        this.isMan = isMan;
        professions = new HashMap<>();
    }

    public boolean addProfession(Profession profession) {
        if (hasProfession(profession.getProfessionName()))
            return false;
        professions.put(profession.getProfessionName(), profession);
        profession.setShorty(this);
        return true;
    }

    public boolean hasProfession(String pofessionName) {
        return professions.containsKey(pofessionName);
    }

    public Profession getProfession(String pofessionName) {
        return professions.get(pofessionName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shorty shorty = (Shorty) o;
        return isMan == shorty.isMan &&
                name.equals(shorty.name) &&
                professions.equals(shorty.professions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isMan, professions);
    }

    @Override
    public String toString() {
        return "Коротышка (" + (isMan ? "малыш" : "малышка") + ") по имени " + name + ".";
    }
}
