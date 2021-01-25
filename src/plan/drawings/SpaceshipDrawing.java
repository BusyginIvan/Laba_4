package plan.drawings;

import plan.drawings.interfaces.*;
import plan.sketch.SketchInterface;

import java.util.Objects;

public class SpaceshipDrawing extends Drawing implements EditSpaceshipDrawing, ApplySpaceshipDrawing {
    private int maxShorties;
    private double maxPayload;

    public SpaceshipDrawing(SketchInterface sketch) {
        super(sketch);
    }

    @Override
    public boolean setMaxShorties(int maxShorties) {
        if (maxShorties <= 0)
            return false;
        this.maxShorties = maxShorties;
        return true;
    }

    @Override
    public boolean setMaxPayload(double maxPayload) {
        if (maxPayload <= 0)
            return false;
        this.maxPayload = maxPayload;
        return true;
    }

    @Override
    public int getMaxShorties() {
        return maxShorties;
    }

    @Override
    public double getMaxPayload() {
        return maxPayload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceshipDrawing that = (SpaceshipDrawing) o;
        return maxShorties == that.maxShorties &&
                Double.compare(that.maxPayload, maxPayload) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxShorties, maxPayload);
    }
}
