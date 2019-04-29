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
import model.bean.Veiculo;

/**
 *
 * @author Murilo
 */
public class VeiculoDAO {

    public void create(Veiculo p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO veiculo (marca, modelo ,placa, cor, ano)VALUES(?,?,?,?,?)");
            stmt.setString(1, p.getMarca());
            stmt.setString(2, p.getModelo());
            stmt.setString(3, p.getPlaca());
            stmt.setString(4, p.getCor());
            stmt.setInt(5, p.getAno());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Veiculo> read() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Veiculo> veiculos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM veiculo");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Veiculo veiculo = new Veiculo();

                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
                veiculos.add(veiculo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return veiculos;

    }

    public List<Veiculo> lerPorMarca(String marca) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Veiculo> veiculos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE marca LIKE ?");
            stmt.setString(1, "%" + marca + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {

                Veiculo veiculo = new Veiculo();

                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setAno(rs.getInt("ano"));
                veiculos.add(veiculo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return veiculos;

    }

    public void update(Veiculo p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE veiculo SET marca = ? ,modelo = ?,cor = ?, ano = ? WHERE placa = ?");
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

    public void delete(Veiculo p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM veiculo WHERE placa = ?");
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
