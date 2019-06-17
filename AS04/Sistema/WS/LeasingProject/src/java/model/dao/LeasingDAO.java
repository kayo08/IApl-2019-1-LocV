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
            stmt = con.prepareStatement("INSERT INTO leasing (cpf_leasing, plate_leasing, date_leasing, date_devolution, time_leasing, time_devolution)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, p.getCpfLeasing());
            stmt.setString(2, p.getPlateLeasing());
            stmt.setDate(3, p.getDateLeasing());
            stmt.setDate(4, p.getDateDevolution());
            stmt.setTime(5, p.getTimeLeasing());
            stmt.setTime(6, p.getTimeDevolution());
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
                leasing.setPlateLeasing(rs.getString("plate_leasing"));
                leasing.setDateLeasing(rs.getDate("date_leasing"));
                leasing.setDateDevolution(rs.getDate("date_devolution"));
                leasing.setTimeLeasing(rs.getTime("time_leasing"));
                leasing.setTimeDevolution(rs.getTime("time_devolution"));
                leasing.setNumberLeasing(rs.getInt("number_leasing"));
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
        String sql = "SELECT * FROM leasing where number_leasing=?";
        Leasing pdao = null;

        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {

            pst.setInt(1, leasing.getNumberLeasing());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                pdao = new Leasing();
                pdao.setCpfLeasing(res.getString("cpf_leasing"));
                pdao.setPlateLeasing(res.getString("plate_leasing"));
                pdao.setDateLeasing(res.getDate("date_leasing"));
                pdao.setDateDevolution(res.getDate("date_devolution"));
                pdao.setTimeLeasing(res.getTime("time_leasing"));
                pdao.setTimeDevolution(res.getTime("time_devolution"));
                pdao.setNumberLeasing(res.getInt("number_leasing"));
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
                leasing.setPlateLeasing(rs.getString("plate_leasing"));
                leasing.setDateLeasing(rs.getDate("date_leasing"));
                leasing.setDateDevolution(rs.getDate("date_devolution"));
                leasing.setTimeLeasing(rs.getTime("time_leasing"));
                leasing.setTimeDevolution(rs.getTime("time_devolution"));
                leasing.setNumberLeasing(rs.getInt("number_leasing"));
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
            stmt = con.prepareStatement("UPDATE leasing SET cpf_leasing = ? ,plate_leasing = ?,date_leasing = ?, date_devolution = ?, time_leasing = ?, time_devolution = ? WHERE number_leasing = ?");
            stmt.setString(1, p.getCpfLeasing());
            stmt.setString(2, p.getPlateLeasing());
            stmt.setDate(3, p.getDateLeasing());
            stmt.setDate(4, p.getDateDevolution());
            stmt.setTime(5, p.getTimeLeasing());
            stmt.setTime(6, p.getTimeDevolution());
            stmt.setInt(7, p.getNumberLeasing());
            stmt.executeUpdate();

            stmt.executeUpdate();

            System.out.println("Successfully updated");
        } catch (SQLException ex) {
            System.out.println("Error updating: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Leasing p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM leasing WHERE number_leasing = ?");
            stmt.setInt(1, p.getNumberLeasing());
            stmt.executeUpdate();

            System.out.println("Successfully deleted");
        } catch (SQLException ex) {
            System.out.println("Error deleting:" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
