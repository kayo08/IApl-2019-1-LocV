/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import json.JSONObject;

/**
 *
 * @author Murilo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Veiculo {
    @XmlAttribute
    private String placa;
    @XmlElement
    private String modelo;
    @XmlElement
    private String marca;
    @XmlElement
    private String cor;
    @XmlElement
    private int ano;

        public Veiculo(){
        
    }
        public Veiculo(JSONObject json) {
        this.marca = json.getString("marca");
        this.modelo = json.getString("modelo");
        this.placa = json.getString("placa");
        this.cor = json.getString("cor");
        this.ano = json.getInt("ano");
    }

    public JSONObject toJson() {
        json.JSONObject json = new JSONObject();
        json.put("marca", this.marca);
        json.put("modelo", this.modelo);
        json.put("placa",this.placa);
        json.put("cor", this.cor);
        json.put("ano", this.ano);
        return json;
    }
    
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    
}
