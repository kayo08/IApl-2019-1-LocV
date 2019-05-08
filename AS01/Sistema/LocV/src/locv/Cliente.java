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
public class Cliente {

    private String nome, cpf, rg, nacionalidade, telefone, nascimento,sexo;
    ArrayList<Cliente> ListaClientes;

    public Cliente() {
        ListaClientes = new ArrayList();
    }
    public Cliente(String nome, String cpf, String rg, String nacionalidade, String telefone, String nascimento, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.sexo = sexo;
        ListaClientes = new ArrayList();
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

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public ArrayList<Cliente> getListaClientes() {
        return ListaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> ListaClientes) {
        this.ListaClientes = ListaClientes;
    }

    public void addCliente(Cliente F) {
        ListaClientes.add(F);
    }

}
