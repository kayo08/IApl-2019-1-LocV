/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locv;

import java.util.ArrayList;

/**
 *
 * @author Murilo
 */
public class Veiculo {

    private String marca, modelo, placa, cor;
    private int ano;
    ArrayList<Veiculo> ListaVeiculos;
  
   public Veiculo(){
       ListaVeiculos = new ArrayList();
   }

    public Veiculo(String marca, String modelo, String placa, String cor, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.cor = cor;
        this.ano = ano;
       ListaVeiculos = new ArrayList();
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
