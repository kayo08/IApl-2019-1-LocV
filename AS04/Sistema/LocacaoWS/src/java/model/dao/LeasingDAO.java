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
import model.bean.Leasing;

/**
 *
 * @author Murilo
 */
public class LeasingDAO {

    public void create(Leasing p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO leasing (cpf_leasing, placa_leasing, data_leasing, data_devolucao, hora_leasing, hora_devolucao)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, p.getCpfLeasing());
            stmt.setString(2, p.getPlacaLeasing());
            stmt.setDate(3, p.getDataLeasing());
            stmt.setDate(4, p.getDataDevolucao());
            stmt.setTime(5, p.getHorarioLeasing());
            stmt.setTime(6, p.getHorarioDevolucao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Leasing> read() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Leasing> locacoes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM leasing");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Leasing leasing = new Leasing();

                leasing.setCpfLeasing(rs.getString("cpf_leasing"));
                leasing.setPlacaLeasing(rs.getString("placa_leasing"));
                leasing.setDataLeasing(rs.getDate("data_leasing"));
                leasing.setDataDevolucao(rs.getDate("data_devolucao"));
                leasing.setHorarioLeasing(rs.getTime("hora_leasing"));
                leasing.setHorarioDevolucao(rs.getTime("hora_devolucao"));
                leasing.setNumeroLeasing(rs.getInt("numero_leasing"));
                locacoes.add(leasing);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeasingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return locacoes;

    }

    public Leasing search(Leasing leasing) {
        String sql = "SELECT * FROM leasing where numero_leasing=?";
        Leasing pdao = null;

        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {

            pst.setInt(1, leasing.getNumeroLeasing());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                pdao = new Leasing();
                pdao.setCpfLeasing(res.getString("cpf_leasing"));
                pdao.setPlacaLeasing(res.getString("placa_leasing"));
                pdao.setDataLeasing(res.getDate("data_leasing"));
                pdao.setDataDevolucao(res.getDate("data_devolucao"));
                pdao.setHorarioLeasing(res.getTime("hora_leasing"));
                pdao.setHorarioDevolucao(res.getTime("hora_devolucao"));
                pdao.setNumeroLeasing(res.getInt("numero_leasing"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdao;
    }

    public List<Leasing> lerPorCpf(String cpf) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Leasing> locacoes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM leasing WHERE cpf_leasing LIKE ?");
            stmt.setString(1, "%" + cpf + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Leasing leasing = new Leasing();

                leasing.setCpfLeasing(rs.getString("cpf_leasing"));
                leasing.setPlacaLeasing(rs.getString("placa_leasing"));
                leasing.setDataLeasing(rs.getDate("data_leasing"));
                leasing.setDataDevolucao(rs.getDate("data_devolucao"));
                leasing.setHorarioLeasing(rs.getTime("hora_leasing"));
                leasing.setHorarioDevolucao(rs.getTime("hora_devolucao"));
                leasing.setNumeroLeasing(rs.getInt("numero_leasing"));
                locacoes.add(leasing);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LeasingDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return locacoes;

    }

    public void update(Leasing p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE leasing SET cpf_leasing = ? ,placa_leasing = ?,data_leasing = ?, data_devolucao = ?, hora_leasing = ?, hora_devolucao = ? WHERE numero_leasing = ?");
            stmt.setString(1, p.getCpfLeasing());
            stmt.setString(2, p.getPlacaLeasing());
            stmt.setDate(3, p.getDataLeasing());
            stmt.setDate(4, p.getDataDevolucao());
            stmt.setTime(5, p.getHorarioLeasing());
            stmt.setTime(6, p.getHorarioDevolucao());
            stmt.setInt(7, p.getNumeroLeasing());
            stmt.executeUpdate();

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Leasing p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM leasing WHERE numero_leasing = ?");
            stmt.setInt(1, p.getNumeroLeasing());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
