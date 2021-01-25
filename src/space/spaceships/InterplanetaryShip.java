package space.spaceships;

import plan.drawings.interfaces.ApplyMainRocketDrawing;
import plan.drawings.interfaces.ApplyMultistageSpaceshipDrawing;
import plan.drawings.interfaces.ApplyRocketDrawing;
import space.space_objects.SpaceObject;
import space.space_objects.SpaceObjectType;
import space.space_objects.UnsuitableSpaceObjectException;

import java.util.ArrayList;
import java.util.Objects;

public class InterplanetaryShip extends Spaceship {
    //double GRAVITATIONAL_CONSTANT = 6.67e-11;
    protected final static double SHORT_TIME = 5;

    private MainRocket mainRocket;
    private ArrayList<Rocket> steps;

    private double speed;
    private boolean gravity;
    private SpaceObject planet;

    public InterplanetaryShip(ApplyMultistageSpaceshipDrawing drawing, SpaceObject startPlanet) {
        super(drawing);

        if (startPlanet.TYPE != SpaceObjectType.PLANET)
            throw new UnsuitableSpaceObjectException("Космический корабль может быть создан только на планете.", SpaceObjectType.PLANET, startPlanet.TYPE);

        mainRocket = new MainRocket(drawing.getMainRocket());
        steps = new ArrayList<>();
        for (int i = 0; i < drawing.getStepsNumber(); i++)
            steps.add(new Rocket(drawing.getStep(i)));
        planet = startPlanet;
        gravity = true;
    }

    public double getMass() {
        double mass = getPayload();
        for (Rocket rocket: steps)
            mass += rocket.getMass();
        return mass + mainRocket.getMass();
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    @Override
    public void start() {
        if (planet == null) // Может ещё проверку на скорость.
            return;
        System.out.println("Три, два, один... Поехали!");
        if (gravity) {
            before_first_space_speed: {
                for (int i = steps.size() - 1; i >= 0; i--) {
                    if (steps.get(i).accelerationBeforeFirstSpaceSpeed())
                        break before_first_space_speed;
                    steps.remove(i);
                    System.out.println("Отделение ступени.");
                }
                System.out.println("Включение главного двигателя.");
                mainRocket.accelerationBeforeFirstSpaceSpeed(); // Исключение в случае неудачи.
            }
            System.out.println("Корабль выходит на эллиптическую орбиту. Его скорость постепенно приблежается ко второй космической.");
        } else {
            System.out.println("Несмотря на отсутствие гравитации, стоит разогнаться до второй космической скорости.");
        }
        if (steps.size() > 0) {
            for (int i = steps.size() - 1; i >= 0; i--) {
                steps.get(i).accelerationToSecondSpaceSpeed();
                steps.remove(i);
            }
            System.out.println("Были израсходованы все дополнительные ступени.");
        }
        if (speed < planet.secondSpaceSpeed()) {
            mainRocket.accelerationToSecondSpaceSpeed();
            System.out.println("Был использован главный двигатель.");
        }
        planet = null;
    }

    public boolean maneuver() {
        if (mainRocket.maneuver()) {
            System.out.println("Корабль совершает манёвр для разворота на 180° вокруг своей оси.");
            return true;
        }
        return false;
    }

    public void moveByInertia(double distance) {
        System.out.println("Полёт по инерции продлился около " + (int)(distance / speed / 3600) + " часов.");
    }

    public void landing(SpaceObject spaceObject) {
        if (this.planet != null || spaceObject.TYPE != SpaceObjectType.PLANET)
            return;

        if (planet.HAS_ATMOSPHERE) {
            speed = 0;
            System.out.println("Выпуск парашутов.");
        } else {
            System.out.println("Торможение при помощи реактивного двигателя.");
            mainRocket.braking();
        }

        this.planet = planet;
        System.out.println("Посадка успешно совершена!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterplanetaryShip that = (InterplanetaryShip) o;
        return Double.compare(that.speed, speed) == 0 &&
                gravity == that.gravity &&
                mainRocket.equals(that.mainRocket) &&
                steps.equals(that.steps) &&
                Objects.equals(planet, that.planet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainRocket, steps, speed, gravity, planet);
    }

    @Override
    public String toString() {
        return "InterplanetaryShip{" +
                "mainRocket=" + mainRocket.toString() +
                ", steps=" + steps +
                ", speed=" + speed +
                ", gravity=" + gravity +
                ", planet=" + planet +
                '}';
    }

    class Rocket {
        private double fuelMass;
        private double hullMass;
        private double fuelConsumption;
        private double fuelSpeed;

        Rocket(ApplyRocketDrawing drawing) {
            fuelMass = drawing.getHullMass();
            hullMass = drawing.getFuelMass();
            fuelConsumption = drawing.getFuelConsumption();
            fuelSpeed = drawing.getFuelSpeed();
        }

        public double getMass() {
            return hullMass + fuelMass;
        }

        public boolean speedChangeInShortTime() {
            double expense = SHORT_TIME * fuelConsumption;
            if (fuelMass < expense)
                return false;
            fuelMass -= expense;
            speed += fuelSpeed * expense / InterplanetaryShip.this.getMass();
            return true;
        }

        public boolean accelerationBeforeFirstSpaceSpeed() {
            while (speedChangeInShortTime()) {
                double centripetalAcceleration = planet.ACCELERATION_OF_GRAVITY - speed * speed / planet.RADIUS;
                if (centripetalAcceleration <= 0) return true;
                speed -= centripetalAcceleration * SHORT_TIME;
            }
            System.out.printf("В результате работы ступени корабль достиг скорости %d м/с.\n", (int)speed);
            return false;
        }

        public void accelerationToSecondSpaceSpeed() {
            // Очень сложные вычисления.
            fuelMass = 0;
            speed = planet.secondSpaceSpeed() + 1000;
        }

        public double getFuelMass() {
            return fuelMass;
        }

        public double getHullMass() {
            return hullMass;
        }

        public double getFuelConsumption() {
            return fuelConsumption;
        }

        public double getFuelSpeed() {
            return fuelSpeed;
        }

        public boolean setFuelMass(double fuelMass) {
            if (fuelMass < 0)
                return false;
            this.fuelMass = fuelMass;
            return true;
        }

        public boolean setHullMass(double hullMass) {
            if (hullMass <= 0)
                return false;
            this.hullMass = hullMass;
            return true;
        }

        public boolean setFuelConsumption(double fuelConsumption) {
            if (fuelConsumption <= 0)
                return false;
            this.fuelConsumption = fuelConsumption;
            return true;
        }

        public boolean setFuelSpeed(double fuelSpeed) {
            if (fuelSpeed <= 0)
                return false;
            this.fuelSpeed = fuelSpeed;
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rocket rocket = (Rocket) o;
            return Double.compare(rocket.fuelMass, fuelMass) == 0 &&
                    Double.compare(rocket.hullMass, hullMass) == 0 &&
                    Double.compare(rocket.fuelConsumption, fuelConsumption) == 0 &&
                    Double.compare(rocket.fuelSpeed, fuelSpeed) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(fuelMass, hullMass, fuelConsumption, fuelSpeed);
        }

        @Override
        public String toString() {
            return "Rocket{" +
                    "fuelMass=" + fuelMass +
                    ", hullMass=" + hullMass +
                    ", fuelConsumption=" + fuelConsumption +
                    ", fuelSpeed=" + fuelSpeed +
                    '}';
        }
    }

    class MainRocket extends Rocket {
        private double maneuverFuel;
        //private double maneuverTime;
        private boolean accelerate;

        MainRocket(ApplyMainRocketDrawing drawing) {
            super(drawing);
            maneuverFuel = drawing.getManeuverFuel();
            //maneuverTime = drawing.getManeuverTime();
            accelerate = true;
        }

        public boolean maneuver() {
            if (getFuelMass() < maneuverFuel || planet != null)
                return false;
            setFuelMass(getFuelMass() - maneuverFuel);
            accelerate = !accelerate;
            return true;
        }

        @Override
        public void accelerationToSecondSpaceSpeed() {
            // Очень сложные вычисления.
            setFuelMass(getFuelMass() - 10000);
            speed = planet.secondSpaceSpeed();
        }

        public boolean braking() {
            if (accelerate)
                maneuver();
            // Очень сложные вычисления.
            setFuelMass(getFuelMass() / 2);
            speed = 0;
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            MainRocket that = (MainRocket) o;
            return Double.compare(that.maneuverFuel, maneuverFuel) == 0 &&
                    accelerate == that.accelerate &&
                    Double.compare(getHullMass(), that.getHullMass()) == 0 &&
                    Double.compare(getFuelSpeed(), that.getFuelSpeed()) == 0 &&
                    Double.compare(getFuelConsumption(), that.getFuelConsumption()) == 0 &&
                    Double.compare(getFuelMass(), that.getFuelMass()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), maneuverFuel, accelerate);
        }

        @Override
        public String toString() {
            return "MainRocket{" +
                    "fuelMass=" + getFuelMass() +
                    ", hullMass=" + getHullMass() +
                    ", fuelConsumption=" + getFuelConsumption() +
                    ", fuelSpeed=" + getFuelSpeed() +
                    ", maneuverFuel=" + maneuverFuel +
                    ", accelerate=" + accelerate +
                    '}';
        }
    }
}