/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.sql.Date;
import json.JSONObject;

/**
 *
 * @author Murilo
 */
@XStreamAlias("Locacao")
public class Cliente {
    @XStreamAsAttribute
    private String cpf;
    private String nome, rg, nacionalidade, telefone, sexo;
    private Date nascimento;
    
    public Cliente(){
        
    }
        public Cliente(JSONObject json) {
        this.nome = json.getString("nome");
        this.cpf = json.getString("cpf");
        this.nacionalidade = json.getString("nacionalidade");
        this.telefone = json.getString("telefone");
        this.sexo = json.getString("sexo");
        this.nascimento = (Date) json.get("nascimento");
    }

    public JSONObject toJson() {
        json.JSONObject json = new JSONObject();
        json.put("nome", this.nome);
        json.put("cpf", this.cpf);
        json.put("rg",this.rg);
        json.put("nacionalidade", this.nacionalidade);
        json.put("telefone", this.telefone);
        json.put("sexo", this.sexo);
        json.put("nascimento", this.nascimento);
        return json;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
    
    
    
}
