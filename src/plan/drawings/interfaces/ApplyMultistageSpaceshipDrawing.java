package plan.drawings.interfaces;

public interface ApplyMultistageSpaceshipDrawing extends ApplySpaceshipDrawing {
    ApplyRocketDrawing getStep(int i);
    int getStepsNumber();
    ApplyMainRocketDrawing getMainRocket();
}