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
import model.bean.Vehicle;

/**
 *
 * @author Murilo
 */
public class VehicleDAO {

    public void create(Vehicle p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO vehicle (marca, modelo ,placa, cor, ano)VALUES(?,?,?,?,?)");
            stmt.setString(1, p.getMarca());
            stmt.setString(2, p.getModelo());
            stmt.setString(3, p.getPlaca());
            stmt.setString(4, p.getCor());
            stmt.setInt(5, p.getAno());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Vehicle> read() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Vehicle> vehicles = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM vehicle");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Vehicle vehicle = new Vehicle();

                vehicle.setMarca(rs.getString("marca"));
                vehicle.setModelo(rs.getString("modelo"));
                vehicle.setPlaca(rs.getString("placa"));
                vehicle.setCor(rs.getString("cor"));
                vehicle.setAno(rs.getInt("ano"));
                vehicles.add(vehicle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vehicles;

    }

    public Vehicle search(Vehicle vehicle) {
        String sql = "SELECT * FROM vehicle where placa=?";
        Vehicle pdao = null;

        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {

            pst.setString(1, vehicle.getPlaca());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                pdao = new Vehicle();
                pdao.setMarca(res.getString("marca"));
                pdao.setModelo(res.getString("modelo"));
                pdao.setAno(res.getInt("ano"));
                pdao.setPlaca(res.getString("placa"));
                pdao.setCor(res.getString("cor"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdao;
    }

    public List<Vehicle> lerPorMarca(String marca) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Vehicle> vehicles = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM vehicle WHERE marca LIKE ?");
            stmt.setString(1, "%" + marca + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Vehicle vehicle = new Vehicle();

                vehicle.setMarca(rs.getString("marca"));
                vehicle.setModelo(rs.getString("modelo"));
                vehicle.setPlaca(rs.getString("placa"));
                vehicle.setCor(rs.getString("cor"));
                vehicle.setAno(rs.getInt("ano"));
                vehicles.add(vehicle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vehicles;

    }

    public void update(Vehicle p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE vehicle SET marca = ? ,modelo = ?,cor = ?, ano = ? WHERE placa = ?");
            stmt.setString(1, p.getMarca());
            stmt.setString(2, p.getModelo());
            stmt.setString(3, p.getCor());
            stmt.setInt(4, p.getAno());
            stmt.setString(5, p.getPlaca());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Vehicle p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM vehicle WHERE placa = ?");
            stmt.setString(1, p.getPlaca());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
