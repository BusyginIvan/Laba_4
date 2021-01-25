import plan.drawings.MultistageSpaceshipDrawing;
import plan.drawings.interfaces.EditMainRocketDrawing;
import plan.drawings.interfaces.EditMultistageSpaceshipDrawing;
import plan.drawings.interfaces.EditRocketDrawing;
import plan.sketch.Sketch;
import plan.sketch.SketchException;
import shorties.professions.Cosmonaut;
import shorties.professions.Engineer;
import shorties.professions.Scientist;
import shorties.Shorty;
import space.spaceships.InterplanetaryShip;
import space.space_objects.SpaceObject;
import space.space_objects.SpaceObjectType;
import space.space_objects.UnsuitableSpaceObjectException;

public class Story {
    public static void main (String[] args) throws Exception {
        main(args);
        try {
            Shorty Znayka = new Shorty("Знайка", true);
            Znayka.addProfession(new Cosmonaut(70));
            Znayka.addProfession(new Scientist(150));
            Znayka.addProfession(new Engineer() {
                @Override
                public void changeDrawing(EditMultistageSpaceshipDrawing drawing) {
                    System.out.println(getName() + " разработал двоякую систему управления для главной ступени корабля, лично спроектировал двигатель.");
                    EditMainRocketDrawing mainRocket = drawing.getMainRocket();
                    mainRocket.setManeuverFuel(200);
                    //mainRocket.setManeuverTime(1000);
                    mainRocket.setHullMass(15000);
                    mainRocket.setFuelSpeed(50000);
                    mainRocket.setFuelConsumption(50);
                    mainRocket.setFuelMass(20000);
                }
            });

            Shorty Zvezdochkin = new Shorty("Звёздочкин", true);
            Zvezdochkin.addProfession(new Engineer() {
                @Override
                public void changeDrawing(EditMultistageSpaceshipDrawing drawing) {
                    int n = 12;
                    System.out.println(getName() + " предложил сделать корабль " + n + "-местным.");
                    drawing.setMaxShorties(n);
                }
            });
            Zvezdochkin.addProfession(new Scientist(135));

            Shorty Fuchsia = new Shorty("Фуксия", false);
            Fuchsia.addProfession(new Cosmonaut(60));
            Fuchsia.addProfession(new Scientist(140));

            Shorty Seledochka = new Shorty("Селёдочка", false);
            Seledochka.addProfession(new Scientist(130));

            System.out.println("Знайка, Фуксия и Селедочка, а также профессор Звездочкин включились в работу по проектированию межпланетного корабля.");

            Sketch sketch = new Sketch("космический корабль",
                    Scientist.extract(Znayka), Scientist.extract(Zvezdochkin), Scientist.extract(Fuchsia), Scientist.extract(Seledochka));

            System.out.println();

            MultistageSpaceshipDrawing drawing = new MultistageSpaceshipDrawing(sketch);
            Engineer.extract(Zvezdochkin).changeDrawing(drawing);
            Engineer.extract(Znayka).changeDrawing(drawing);
            new Engineer() {
                @Override
                public void changeDrawing(EditMultistageSpaceshipDrawing drawing) {
                    System.out.println("Спроектирован корпус первой ступени корабля.");
                    EditRocketDrawing rocket = drawing.getStep(2);
                    rocket.setHullMass(50000);
                    rocket.setFuelMass(500000);
                }
            }.changeDrawing(drawing);
            new Engineer() {
                @Override
                public void changeDrawing(EditMultistageSpaceshipDrawing drawing) {
                    System.out.println("Проработана система подачи и поджига топлива в первой ступени.");
                    EditRocketDrawing rocket = drawing.getStep(2);
                    rocket.setFuelSpeed(35000);
                    rocket.setFuelConsumption(5000);
                }
            }.changeDrawing(drawing);
            new Engineer() {
                @Override
                public void changeDrawing(EditMultistageSpaceshipDrawing drawing) {
                    System.out.println("Выполнены чертежи двух дополнительных ступеней.");
                    EditRocketDrawing rocket = drawing.getStep(0);
                    rocket.setHullMass(20000);
                    rocket.setFuelSpeed(40000);
                    rocket.setFuelConsumption(2000);
                    rocket.setFuelMass(150000);
                }
            }.changeDrawing(drawing);

            System.out.println();

            double distanceToMoon = 384400000;
            SpaceObject earth = new SpaceObject(SpaceObjectType.PLANET, 5.97e24, 6371000, 9.8, true);
            SpaceObject moon = new SpaceObject(SpaceObjectType.PLANET, 7.35e22, 1737100, 1.62, false);
            InterplanetaryShip spaceship = new InterplanetaryShip(drawing, earth);

            spaceship.start();
            spaceship.maneuver();
            spaceship.moveByInertia(distanceToMoon);
            spaceship.landing(moon);

            System.out.println();

            spaceship.start();
            spaceship.moveByInertia(distanceToMoon);
            spaceship.landing(earth);

        } catch (SketchException e) {
            System.out.println(e.getMessage());
            System.out.println("Требовалось суммарное IQ, не менше " + e.getMinSumIQ() + ".");
            System.out.println("У учёных сумма оказалась равной только " + e.getRealSumIQ() + ".");
        } catch (UnsuitableSpaceObjectException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }
}