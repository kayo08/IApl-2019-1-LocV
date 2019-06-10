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
            stmt = con.prepareStatement("INSERT INTO client (nome, cpf, rg, nacionalidade, telefone, sexo, nascimento)VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getRg());
            stmt.setString(4, p.getNacionalidade());
            stmt.setString(5, p.getTelefone());
            stmt.setString(6, p.getSexo());
            stmt.setDate(7, p.getNascimento());
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

                client.setNome(rs.getString("nome"));
                client.setCpf(rs.getString("cpf"));
                client.setRg(rs.getString("rg"));
                client.setNacionalidade(rs.getString("nacionalidade"));
                client.setTelefone(rs.getString("telefone"));
                client.setSexo(rs.getString("sexo"));
                client.setNascimento(rs.getDate("nascimento"));
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
                pdao.setNome(res.getString("nome"));
                pdao.setCpf(res.getString("cpf"));
                pdao.setSexo(res.getString("sexo"));
                pdao.setNascimento(res.getDate("nascimento"));
                pdao.setRg(res.getString("rg"));
                pdao.setNacionalidade(res.getString("nacionalidade"));
                pdao.setTelefone(res.getString("telefone"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdao;
    }

    public List<Client> lerPorNome(String nome) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Client> clients = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM client WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Client client = new Client();

                client.setNome(rs.getString("nome"));
                client.setCpf(rs.getString("cpf"));
                client.setRg(rs.getString("rg"));
                client.setNacionalidade(rs.getString("nacionalidade"));
                client.setTelefone(rs.getString("telefone"));
                client.setSexo(rs.getString("sexo"));
                client.setNascimento(rs.getDate("nascimento"));
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
            stmt = con.prepareStatement("UPDATE client SET nome = ? ,rg = ?,nacionalidade = ?, telefone = ?, sexo = ?, nascimento = ? WHERE cpf = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getRg());
            stmt.setString(3, p.getNacionalidade());
            stmt.setString(4, p.getTelefone());
            stmt.setString(5, p.getSexo());
            stmt.setDate(6, p.getNascimento());
            stmt.setString(7, p.getCpf());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
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

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
