package movie;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Integer x; //Поле не может быть null
    private float y;

    public Coordinates(int x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(int x) {
        this.x = x;
    }

    /**
     * @return string with coordinates
     */
    public Integer getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getCoordinates() {
        return "x = " + this.x + ", y = " + this.y;
    }
}
