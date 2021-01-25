package plan.drawings.interfaces;

public interface EditMultistageSpaceshipDrawing extends EditSpaceshipDrawing {
    EditRocketDrawing getStep(int i);
    EditMainRocketDrawing getMainRocket();
}
