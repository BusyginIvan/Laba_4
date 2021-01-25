package plan.drawings;

import plan.drawings.interfaces.*;
import plan.sketch.SketchInterface;

import java.util.Objects;

public class MultistageSpaceshipDrawing extends SpaceshipDrawing implements EditMultistageSpaceshipDrawing, ApplyMultistageSpaceshipDrawing {
    private MainRocket mainRocket;
    private Rocket firstAndSecondSteps;
    private Rocket thirdStep;

    public MultistageSpaceshipDrawing(SketchInterface sketch) {
        super(sketch);
        mainRocket = new MainRocket();
        thirdStep = new Rocket();
        firstAndSecondSteps = new Rocket();
    }

    @Override
    public MainRocket getMainRocket() {
        return mainRocket;
    }

    @Override
    public Rocket getStep(int i) {
        if (i == 0 || i == 1)
            return firstAndSecondSteps;
        else if (i == 2)
            return thirdStep;
        return null;
    }

    @Override
    public int getStepsNumber() {
        return 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MultistageSpaceshipDrawing that = (MultistageSpaceshipDrawing) o;
        return mainRocket.equals(that.mainRocket) &&
                firstAndSecondSteps.equals(that.firstAndSecondSteps) &&
                thirdStep.equals(that.thirdStep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mainRocket, firstAndSecondSteps, thirdStep);
    }

    public class Rocket implements EditRocketDrawing, ApplyRocketDrawing {
        private double hullMass;
        private double fuelSpeed;
        private double fuelConsumption;
        private double fuelMass;

        @Override
        public boolean setHullMass(double hullMass) {
            if (hullMass <= 0)
                return false;
            this.hullMass = hullMass;
            return true;
        }

        @Override
        public boolean setFuelSpeed(double fuelSpeed) {
            if (fuelSpeed <= 0)
                return false;
            this.fuelSpeed = fuelSpeed;
            return true;
        }

        @Override
        public boolean setFuelConsumption(double fuelConsumption) {
            if (fuelConsumption <= 0)
                return false;
            this.fuelConsumption = fuelConsumption;
            return true;
        }

        @Override
        public boolean setFuelMass(double fuelMass) {
            if (fuelMass <= 0)
                return false;
            this.fuelMass = fuelMass;
            return true;
        }

        @Override
        public double getHullMass() {
            return hullMass;
        }

        @Override
        public double getFuelSpeed() {
            return fuelSpeed;
        }

        @Override
        public double getFuelConsumption() {
            return fuelConsumption;
        }

        @Override
        public double getFuelMass() {
            return fuelMass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rocket rocket = (Rocket) o;
            return Double.compare(rocket.hullMass, hullMass) == 0 &&
                    Double.compare(rocket.fuelSpeed, fuelSpeed) == 0 &&
                    Double.compare(rocket.fuelConsumption, fuelConsumption) == 0 &&
                    Double.compare(rocket.fuelMass, fuelMass) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(hullMass, fuelSpeed, fuelConsumption, fuelMass);
        }
    }

    class MainRocket extends Rocket implements EditMainRocketDrawing, ApplyMainRocketDrawing {
        private double maneuverFuel;
        //private double maneuverTime;

        @Override
        public boolean setManeuverFuel(double maneuverFuel) {
            if (maneuverFuel <= 0)
                return false;
            this.maneuverFuel = maneuverFuel;
            return true;
        }

        @Override
        public double getManeuverFuel() {
            return maneuverFuel;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            MainRocket that = (MainRocket) o;
            return Double.compare(that.maneuverFuel, maneuverFuel) == 0 &&
                    Double.compare(getHullMass(), that.getHullMass()) == 0 &&
                    Double.compare(getFuelSpeed(), that.getFuelSpeed()) == 0 &&
                    Double.compare(getFuelConsumption(), that.getFuelConsumption()) == 0 &&
                    Double.compare(getFuelMass(), that.getFuelMass()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), maneuverFuel);
        }

        /*
        @Override
        public boolean setManeuverTime(double maneuverTime) {
            if (maneuverTime <= 0)
                return false;
            this.maneuverTime = maneuverTime;
            return true;
        }

        @Override
        public double getManeuverTime() {
            return maneuverTime;
        }
        */
    }
}
