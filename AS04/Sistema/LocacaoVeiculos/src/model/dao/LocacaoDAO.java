/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Locacao;

/**
 *
 * @author Murilo
 */
public class LocacaoDAO {
    
     public void create(Locacao p) {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO locacao (cpf_locacao, placa_locacao, data_locacao, data_devolucao, hora_locacao, hora_devolucao)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, p.getCpfLocacao());
            stmt.setString(2, p.getPlacaLocacao());
            stmt.setDate(3, p.getDataLocacao());
            stmt.setDate(4, p.getDataDevolucao());
            stmt.setTime(5, p.getHorarioLocacao());
            stmt.setTime(6, p.getHorarioDevolucao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Locacao> read() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Locacao> locacoes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM locacao");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Locacao locacao = new Locacao();

                locacao.setCpfLocacao(rs.getString("cpf_locacao"));
                locacao.setPlacaLocacao(rs.getString("placa_locacao"));
                locacao.setDataLocacao(rs.getDate("data_locacao"));
                locacao.setDataDevolucao(rs.getDate("data_devolucao"));
                locacao.setHorarioLocacao(rs.getTime("hora_locacao"));
                locacao.setHorarioDevolucao(rs.getTime("hora_devolucao"));
                locacao.setNumeroLocacao(rs.getInt("numero_locacao"));
                locacoes.add(locacao);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return locacoes;

    }
    public List<Locacao> lerPorCpf(String cpf) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Locacao> locacoes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM locacao WHERE cpf_locacao LIKE ?");
            stmt.setString(1, "%"+cpf+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Locacao locacao = new Locacao();

                locacao.setCpfLocacao(rs.getString("cpf_locacao"));
                locacao.setPlacaLocacao(rs.getString("placa_locacao"));
                locacao.setDataLocacao(rs.getDate("data_locacao"));
                locacao.setDataDevolucao(rs.getDate("data_devolucao"));
                locacao.setHorarioLocacao(rs.getTime("hora_locacao"));
                locacao.setHorarioDevolucao(rs.getTime("hora_devolucao"));
                locacao.setNumeroLocacao(rs.getInt("numero_locacao"));
                locacoes.add(locacao);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LocacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return locacoes;

    }

    public void update(Locacao p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE locacao SET cpf_locacao = ? ,placa_locacao = ?,data_locacao = ?, data_devolucao = ?, hora_locacao = ?, hora_devolucao = ? WHERE numero_locacao = ?");
            stmt.setString(1, p.getCpfLocacao());
            stmt.setString(2, p.getPlacaLocacao());
            stmt.setDate(3, p.getDataLocacao());
            stmt.setDate(4, p.getDataDevolucao());
            stmt.setTime(5, p.getHorarioLocacao());
            stmt.setTime(6, p.getHorarioDevolucao());
            stmt.setInt(7, p.getNumeroLocacao());
            stmt.executeUpdate();

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Locacao p) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM locacao WHERE numero_locacao = ?");
            stmt.setInt(1, p.getNumeroLocacao());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
