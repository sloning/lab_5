package movie;

public class Location {
    private Integer x; //Поле не может быть null
    private long y;
    private Integer z; //Поле не может быть null
    private String name; //Поле не может быть null

    public Location (String name, int x, long y, int z){
        this.name=name;
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public String getLocation(){
        return "x = " + x + " y = " + y + " z = " + z;
    }
}
