package plan.sketch;

public class SketchException extends Exception {
    private int minSumIQ;
    private int realSumIQ;

    SketchException() {
        super("Для создания общего плана корабля группе учёных не хватило мозгов.");
        minSumIQ = -1;
        realSumIQ = -1;
    }

    SketchException(int minSumIQ, int realSumIQ) {
        super("Для создания общего плана корабля группе учёных не хватило мозгов!");
        this.minSumIQ = minSumIQ;
        this.realSumIQ = realSumIQ;
    }

    public int getMinSumIQ() {
        return minSumIQ;
    }

    public int getRealSumIQ() {
        return realSumIQ;
    }
}
