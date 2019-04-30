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
public class Locacao {
    
    private String cpfLocacao, placaLocacao, dataLocacao, dataDevolucao,horarioLocacao, horarioDevolucao;
    private int numeroLocacao;
    ArrayList<Locacao> ListaLocacoes;
    
    public Locacao(){
        ListaLocacoes = new ArrayList();
    }


    public Locacao(String cpfLocacao,String placaLocacao,String dataLocacao, String horarioLocacao,String dataDevolucao, String horarioDevolucao, int numeroLocacao) {
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.horarioLocacao = horarioLocacao;
        this.horarioDevolucao = horarioDevolucao;
        this.numeroLocacao = numeroLocacao;
        this.cpfLocacao = cpfLocacao;
        this.placaLocacao = placaLocacao;
    }

    public String getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(String dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getHorarioLocacao() {
        return horarioLocacao;
    }

    public void setHorarioLocacao(String horarioLocacao) {
        this.horarioLocacao = horarioLocacao;
    }

    public String getHorarioDevolucao() {
        return horarioDevolucao;
    }

    public void setHorarioDevolucao(String horarioDevolucao) {
        this.horarioDevolucao = horarioDevolucao;
    }

    public int getNumeroLocacao() {
        return numeroLocacao;
    }

    public void setNumeroLocacao(int numeroLocacao) {
        this.numeroLocacao = numeroLocacao;
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
    
    
    
}
