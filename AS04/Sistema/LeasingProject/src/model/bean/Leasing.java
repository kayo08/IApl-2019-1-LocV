/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.sql.Date;
import java.sql.Time;
import json.JSONObject;

/**
 *
 * @author Murilo
 */
public class Leasing {

    private String cpfLeasing, plateLeasing;
    private Date dateLeasing, dateDevolution;
    private Time timeLeasing, timeDevolution;
    @XStreamAsAttribute
    private int numberLeasing;

    public Leasing() {

    }

    public Leasing(JSONObject json) {
        this.numberLeasing = json.getInt("number_leasing");
        this.cpfLeasing = json.getString("cpf_leasing");
        this.plateLeasing = json.getString("plate_leasing");
        this.dateLeasing = (Date) json.get("date_leasing");
        this.dateDevolution = (Date) json.get("date_devolution");
        this.timeLeasing = (Time) json.get("time_leasing");
        this.timeDevolution = (Time) json.get("time_devolution");
    }

    public JSONObject toJson() {
        json.JSONObject json = new JSONObject();
        json.put("number_leasing", this.numberLeasing);
        json.put("cpf_leasing", this.cpfLeasing);
        json.put("plate_leasing", this.plateLeasing);
        json.put("date_leasing", this.dateLeasing);
        json.put("time_leasing", this.timeLeasing);
        json.put("date_devolution", this.dateDevolution);
        json.put("time_devolution", this.timeDevolution);
        return json;
    }

    public String getCpfLeasing() {
        return cpfLeasing;
    }

    public void setCpfLeasing(String cpfLeasing) {
        this.cpfLeasing = cpfLeasing;
    }

    public String getPlateLeasing() {
        return plateLeasing;
    }

    public void setPlateLeasing(String plateLeasing) {
        this.plateLeasing = plateLeasing;
    }

    public Date getDateLeasing() {
        return dateLeasing;
    }

    public void setDateLeasing(Date dateLeasing) {
        this.dateLeasing = dateLeasing;
    }

    public Date getDateDevolution() {
        return dateDevolution;
    }

    public void setDateDevolution(Date dateDevolution) {
        this.dateDevolution = dateDevolution;
    }

    public Time getTimeLeasing() {
        return timeLeasing;
    }

    public void setTimeLeasing(Time timeLeasing) {
        this.timeLeasing = timeLeasing;
    }

    public Time getTimeDevolution() {
        return timeDevolution;
    }

    public void setTimeDevolution(Time timeDevolution) {
        this.timeDevolution = timeDevolution;
    }

    public int getNumberLeasing() {
        return numberLeasing;
    }

    public void setNumberLeasing(int numberLeasing) {
        this.numberLeasing = numberLeasing;
    }

}
