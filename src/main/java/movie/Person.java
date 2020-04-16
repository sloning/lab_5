package movie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private Float weight; //Поле может быть null, Значение поля должно быть больше 0
    private Location location; //Поле может быть null

    /**
     * Constructor, creates person
     *
     * @param name     name of person
     * @param height   height of person
     * @param weight   weight of person
     * @param location location of person
     */
    public Person(String name, Double height, Float weight, Location location) {
        if (weight == 0) {
            this.weight = null;
        } else {
            this.weight = weight;
        }
        if (height == 0) {
            this.height = null;
        } else {
            this.height = height;
        }
        this.name = name;
        this.location = location;
        this.birthday = new Date();
    }

    public Person(String name, Double height, Float weight, Location location, Date date) {
        if (weight == 0) {
            this.weight = null;
        } else {
            this.weight = weight;
        }
        if (height == 0) {
            this.height = null;
        } else {
            this.height = height;
        }
        this.name = name;
        this.location = location;
        this.birthday = date;
    }

    /**
     * @return string with information about person
     */
    public String getPerson() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(this.birthday);
        return "Name: " + name + "\nBirthday: " + date + "\nHeight: " + height + "\nWeight: " + weight + location.getLocation();
    }


}