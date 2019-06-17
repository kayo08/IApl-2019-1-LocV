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
import model.bean.Client;

/**
 *
 * @author Murilo
 */
public class ClientDAO {

    public void create(Client p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO client (name, cpf, rg, nationality, telephone, gender, birth)VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getRg());
            stmt.setString(4, p.getNationality());
            stmt.setString(5, p.getTelephone());
            stmt.setString(6, p.getGender());
            stmt.setDate(7, p.getBirth());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Client> read() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Client> clients = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM client");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Client client = new Client();

                client.setName(rs.getString("name"));
                client.setCpf(rs.getString("cpf"));
                client.setRg(rs.getString("rg"));
                client.setNationality(rs.getString("nationality"));
                client.setTelephone(rs.getString("telephone"));
                client.setGender(rs.getString("gender"));
                client.setBirth(rs.getDate("birth"));
                clients.add(client);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clients;

    }

    public Client search(Client client) {
        String sql = "SELECT * FROM client where cpf=?";
        Client pdao = null;

        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {

            pst.setString(1, client.getCpf());
            ResultSet res = pst.executeQuery();

            if (res.next()) {
                pdao = new Client();
                pdao.setName(res.getString("name"));
                pdao.setCpf(res.getString("cpf"));
                pdao.setGender(res.getString("gender"));
                pdao.setBirth(res.getDate("birth"));
                pdao.setRg(res.getString("rg"));
                pdao.setNationality(res.getString("nationality"));
                pdao.setTelephone(res.getString("telephone"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdao;
    }

    public List<Client> lerPorName(String name) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Client> clients = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM client WHERE name LIKE ?");
            stmt.setString(1, "%" + name + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Client client = new Client();

                client.setName(rs.getString("name"));
                client.setCpf(rs.getString("cpf"));
                client.setRg(rs.getString("rg"));
                client.setNationality(rs.getString("nationality"));
                client.setTelephone(rs.getString("telephone"));
                client.setGender(rs.getString("gender"));
                client.setBirth(rs.getDate("birth"));
                clients.add(client);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return clients;

    }

    public void update(Client p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE client SET name = ? ,rg = ?,nationality = ?, telephone = ?, gender = ?, birth = ? WHERE cpf = ?");
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getRg());
            stmt.setString(3, p.getNationality());
            stmt.setString(4, p.getTelephone());
            stmt.setString(5, p.getGender());
            stmt.setDate(6, p.getBirth());
            stmt.setString(7, p.getCpf());

            stmt.executeUpdate();
            System.out.println("Successfully updated");
        } catch (SQLException ex) {
            System.out.println("Error updating: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Client p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM client WHERE cpf = ?");
            stmt.setString(1, p.getCpf());
            stmt.executeUpdate();
            System.out.println("Successfully deleted");
        } catch (SQLException ex) {
            System.out.println("Error deleting: "+ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
