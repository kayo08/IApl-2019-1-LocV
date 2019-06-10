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

    private String cpfLeasing, placaLeasing;
    private Date dataLeasing, dataDevolucao;
    private Time horarioLeasing, horarioDevolucao;
    private int numeroLeasing;

    public Leasing() {

    }
    public String getCpfLeasing() {
        return cpfLeasing;
    }

    public void setCpfLeasing(String cpfLeasing) {
        this.cpfLeasing = cpfLeasing;
    }

    public String getPlacaLeasing() {
        return placaLeasing;
    }

    public void setPlacaLeasing(String placaLeasing) {
        this.placaLeasing = placaLeasing;
    }

    public Date getDataLeasing() {
        return dataLeasing;
    }

    public void setDataLeasing(Date dataLeasing) {
        this.dataLeasing = dataLeasing;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Time getHorarioLeasing() {
        return horarioLeasing;
    }

    public void setHorarioLeasing(Time horarioLeasing) {
        this.horarioLeasing = horarioLeasing;
    }

    public Time getHorarioDevolucao() {
        return horarioDevolucao;
    }

    public void setHorarioDevolucao(Time horarioDevolucao) {
        this.horarioDevolucao = horarioDevolucao;
    }

    public int getNumeroLeasing() {
        return numeroLeasing;
    }

    public void setNumeroLeasing(int numeroLeasing) {
        this.numeroLeasing = numeroLeasing;
    }

}
