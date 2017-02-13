package main.java.com.Jhatkaa.entity;

/**
 * Created by Vikram Aditya on 2/12/2017.
 */
public class Car {
    private String regNumber;
    private String colour;

    public Car() {
    }

    public Car(String regNumber, String colour) {
        this.regNumber = regNumber;
        this.colour = colour;
    }

    public String getRegNumber() {
        return regNumber;
    }
    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

}

