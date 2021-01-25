package plan.sketch;

import shorties.professions.ScientistInterface;

import java.util.Objects;

public class Sketch implements SketchInterface {
    private String projectName;
    public final String developers;

    public Sketch(String projectName, ScientistInterface... scientists) throws SketchException {
        class CommandIQ {
            private int sum;
            public final int MIN_SUM_IQ = 500;
            private String developers;

            CommandIQ() throws SketchException {
                for (ScientistInterface scientist: scientists)
                    sum += scientist.getIQ();
                if (sum < MIN_SUM_IQ) throw new SketchException(MIN_SUM_IQ, sum);
                developers = " на " + shareOfWork(scientists[0]) + "% проработал " + (scientists[0].isMan() ? "": "а") + scientists[0].getName();
                for (int i = 1; i < scientists.length; i++)
                    developers += ", на " + shareOfWork(scientists[i]) + "% - " + scientists[i].getName();
                developers += ".";
            }

            int shareOfWork(ScientistInterface scientist) {
                return scientist.getIQ() * 100 / sum;
            }

            String developers() {
                return developers;
            }
        }
        CommandIQ commandIQ = new CommandIQ();
        System.out.println("В итоге, эскиз" + commandIQ.developers());
        this.developers = "Эскиз" + commandIQ.developers();

        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Эскиз, на котором детально изображён(-а) " + projectName + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sketch sketch = (Sketch) o;
        return projectName.equals(sketch.projectName) &&
                developers.equals(sketch.developers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, developers);
    }
}