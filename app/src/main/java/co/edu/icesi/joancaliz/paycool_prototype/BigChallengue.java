package co.edu.icesi.joancaliz.paycool_prototype;

//Esta clase es usada para almacenar la información de cada reto.
public class BigChallengue {

    private int points;
    private String title, description, type, img;
    private Boolean complete = false;

    public BigChallengue() {

    }

    public BigChallengue(String title, String description, int points, String type){
        this.title=title;
        this.description=description;
        this.points=points;
        this.type =type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
