/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Murilo
 */
public class Leasing {

    private String cpfLeasing, plateLeasing;
    private Date dateLeasing, dateDevolution;
    private Time timeLeasing, timeDevolution;
    private int numberLeasing;

    public Leasing() {

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
