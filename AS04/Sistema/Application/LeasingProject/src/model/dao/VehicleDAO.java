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
            stmt = con.prepareStatement("INSERT INTO vehicle (brand, model ,plate, color, year)VALUES(?,?,?,?,?)");
            stmt.setString(1, p.getBrand());
            stmt.setString(2, p.getModel());
            stmt.setString(3, p.getPlate());
            stmt.setString(4, p.getColor());
            stmt.setInt(5, p.getYear());
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

                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setPlate(rs.getString("plate"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setYear(rs.getInt("year"));
                vehicles.add(vehicle);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vehicles;

    }

    public List<Vehicle> readByBrand(String brand) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Vehicle> vehicles = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM vehicle WHERE brand LIKE ?");
            stmt.setString(1, "%" + brand + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Vehicle vehicle = new Vehicle();

                vehicle.setBrand(rs.getString("brand"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setPlate(rs.getString("plate"));
                vehicle.setColor(rs.getString("color"));
                vehicle.setYear(rs.getInt("year"));
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
            stmt = con.prepareStatement("UPDATE vehicle SET brand = ? ,model = ?,color = ?, year = ? WHERE plate = ?");
            stmt.setString(1, p.getBrand());
            stmt.setString(2, p.getModel());
            stmt.setString(3, p.getColor());
            stmt.setInt(4, p.getYear());
            stmt.setString(5, p.getPlate());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully updated!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Vehicle p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM vehicle WHERE plate = ?");
            stmt.setString(1, p.getPlate());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Successfully deleted!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
