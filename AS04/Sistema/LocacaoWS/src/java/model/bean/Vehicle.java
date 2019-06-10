/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Murilo
 */
public class Vehicle {
    private String placa;
    private String modelo;
    private String marca;
    private String cor;
    private int ano;
    //private ArrayList<Veiculo> listaVeiculo = new ArrayList<>();
        public Vehicle(){
        
    }

    public Vehicle(Vehicle get) {
        this.placa = get.placa;
        this.modelo = get.modelo;
        this.cor = get.cor;
        this.ano = get.ano;
        this.marca = get.marca;
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
