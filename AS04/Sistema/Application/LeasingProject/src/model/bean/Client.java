/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.sql.Date;
import json.JSONObject;

/**
 *
 * @author Murilo
 */
public class Client {
    @XStreamAsAttribute
    private String cpf;
    private String name, rg, nationality, telephone, gender;
    private Date birth;
    
    public Client(){
        
    }
        public Client(JSONObject json) {
        this.name = json.getString("name");
        this.cpf = json.getString("cpf");
        this.nationality = json.getString("nationality");
        this.telephone = json.getString("telephone");
        this.gender = json.getString("gender");
        this.birth = (Date) json.get("birth");
    }

    public JSONObject toJson() {
        json.JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("cpf", this.cpf);
        json.put("rg",this.rg);
        json.put("nationality", this.nationality);
        json.put("telephone", this.telephone);
        json.put("gender", this.gender);
        json.put("birth", this.birth);
        return json;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
    
    
}
