package co.edu.icesi.joancaliz.paycool_prototype;

//Esta clase es usada para almacenar la información de cada reto.
public class Challengue {
    private int points;
    private String title, description;

    public Challengue() {

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
