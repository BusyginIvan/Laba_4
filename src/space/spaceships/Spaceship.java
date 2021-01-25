package space.spaceships;

import java.util.ArrayList;

import plan.drawings.interfaces.ApplySpaceshipDrawing;
import shorties.professions.CosmonautInterface;

public abstract class Spaceship {
    private final double MAX_PAYLOAD;
    private final int MAX_SHORTIES;

    private ArrayList<CosmonautInterface> cosmonauts;
    private double thingsMass;

    Spaceship(ApplySpaceshipDrawing drawing) {
        MAX_PAYLOAD = drawing.getMaxPayload();
        MAX_SHORTIES = drawing.getMaxShorties();

        cosmonauts = new ArrayList<>();
    }

    public double getPayload() {
        double mass = thingsMass;
        for (CosmonautInterface cosmonaut: cosmonauts)
            mass += cosmonaut.getMass();
        return mass;
    }

    public boolean addCosmonaut(CosmonautInterface cosmonaut) {
        if (cosmonauts.size() < MAX_SHORTIES && !cosmonauts.contains(cosmonaut)) {
            cosmonauts.add(cosmonaut);
            return true;
        }
        return false;
    }

    public boolean addThings(double mass) {
        if (getPayload() + mass <= MAX_PAYLOAD) {
            thingsMass += mass;
            return true;
        }
        return false;
    }

    public boolean setThingsMass(double mass) {
        if (getPayload() - this.thingsMass + mass <= MAX_PAYLOAD) {
            this.thingsMass = mass;
            return true;
        }
        return false;
    }

    public double getThingsMass() {
        return thingsMass;
    }

    public abstract void start();
}
