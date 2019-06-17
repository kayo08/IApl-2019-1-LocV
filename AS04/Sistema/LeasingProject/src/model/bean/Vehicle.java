/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import json.JSONObject;

/**
 *
 * @author Murilo
 */
@XStreamAlias("Veiculo")
public class Vehicle {

    @XStreamAsAttribute
    private String plate;
    private String model;
    private String brand;
    private String color;
    private int year;

    public Vehicle() {

    }

    public Vehicle(Vehicle get) {
        this.plate = get.plate;
        this.model = get.model;
        this.color = get.color;
        this.year = get.year;
        this.brand = get.brand;
    }

    public JSONObject toJson() {
        json.JSONObject json = new JSONObject();
        json.put("brand", this.brand);
        json.put("model", this.model);
        json.put("plate", this.plate);
        json.put("color", this.color);
        json.put("year", this.year);
        return json;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
