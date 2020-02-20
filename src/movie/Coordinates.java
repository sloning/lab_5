package movie;

public class Coordinates {
    private Integer x; //Поле не может быть null
    private float y;

    public Coordinates(int x, float y){
        this.x=x;
        this.y=y;
    }

    public String getCoordinates() {
        return "x = " + this.x + ", y = " + this.y;
    }
}
