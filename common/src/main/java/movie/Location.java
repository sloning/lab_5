package movie;

import java.io.Serializable;

public class Location implements Serializable {
    private Integer x; //Поле не может быть null
    private long y;
    private Integer z; //Поле не может быть null
    private String name; //Поле не может быть null

    public Integer getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public Integer getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public Location(String name, int x, long y, int z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(String name, int x, int z) {
        this.name = name;
        this.x = x;
        this.z = z;
    }

    public Location() {

    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return string with location
     */
    public String getLocation() {
        return "\nLocation name: " + name + "\nLocation: x = " + x + " y = " + y + " z = " + z;
    }
}
