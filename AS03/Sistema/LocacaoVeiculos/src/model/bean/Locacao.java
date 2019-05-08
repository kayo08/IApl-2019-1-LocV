/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.sql.Date;
import java.sql.Time;
import json.JSONObject;

/**
 *
 * @author Murilo
 */
@XStreamAlias("Locacao")
public class Locacao {

    private String cpfLocacao, placaLocacao;
    private Date dataLocacao, dataDevolucao;
    private Time horarioLocacao, horarioDevolucao;
    @XStreamAsAttribute
    private int numeroLocacao;

    public Locacao() {

    }

    public Locacao(JSONObject json) {
        this.numeroLocacao = json.getInt("numero_locacao");
        this.cpfLocacao = json.getString("cpf_locacao");
        this.placaLocacao = json.getString("placa_locacao");
        this.dataLocacao = (Date) json.get("data_locacao");
        this.dataDevolucao = (Date) json.get("data_devolucao");
        this.horarioLocacao = (Time) json.get("horario_locacao");
        this.horarioDevolucao = (Time) json.get("horario_devolucao");
    }

    public JSONObject toJson() {
        json.JSONObject json = new JSONObject();
        json.put("numero_locacao", this.numeroLocacao);
        json.put("cpf_locacao", this.cpfLocacao);
        json.put("placa_locacao", this.placaLocacao);
        json.put("data_locacao", this.dataLocacao);
        json.put("horario_locacao", this.horarioLocacao);
        json.put("data_devolucao", this.dataDevolucao);
        json.put("horario_devolucao", this.horarioDevolucao);
        return json;
    }

    public String getCpfLocacao() {
        return cpfLocacao;
    }

    public void setCpfLocacao(String cpfLocacao) {
        this.cpfLocacao = cpfLocacao;
    }

    public String getPlacaLocacao() {
        return placaLocacao;
    }

    public void setPlacaLocacao(String placaLocacao) {
        this.placaLocacao = placaLocacao;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Time getHorarioLocacao() {
        return horarioLocacao;
    }

    public void setHorarioLocacao(Time horarioLocacao) {
        this.horarioLocacao = horarioLocacao;
    }

    public Time getHorarioDevolucao() {
        return horarioDevolucao;
    }

    public void setHorarioDevolucao(Time horarioDevolucao) {
        this.horarioDevolucao = horarioDevolucao;
    }

    public int getNumeroLocacao() {
        return numeroLocacao;
    }

    public void setNumeroLocacao(int numeroLocacao) {
        this.numeroLocacao = numeroLocacao;
    }

}
