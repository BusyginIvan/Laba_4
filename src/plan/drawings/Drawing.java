package plan.drawings;

import plan.sketch.SketchInterface;

import java.util.Objects;

public class Drawing {
    public final String projectName;

    public Drawing(SketchInterface sketch) {
        projectName = sketch.getProjectName();
    }

    @Override
    public String toString() {
        return "Подробные чертежи, по которым будет сделан(-а) " + projectName + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drawing drawing = (Drawing) o;
        return projectName.equals(drawing.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName);
    }
}

/*public class Drawing {
    private String projectName;
    private ArrayList<String> content;

    public Drawing(SketchInterface sketch) {
        projectName = sketch.getProjectName();
        content = new ArrayList<>();
    }

    public void addComponent(EngineerInterface engineer) {
        content.add(engineer.getСomponent());
    }

    @Override
    public String toString() {
        String description = "Подробные чертежи, по которым будет сделан(-а) " + projectName +
                ". Включает в себя следующие узлы корабля:\n - " + content.get(0);
        for (int i = 1; i < content.size(); i++)
            description += ";\n - " + content.get(i);
        return description + ".";
    }
}*/

