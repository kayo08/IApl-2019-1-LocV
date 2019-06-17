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
import model.bean.User;

/**
 *
 * @author Near
 */
public class UserDAO {
    
    public UserDAO()
    {
    
    }
    
    public boolean insert(User user)
    {
        String sql = "INSERT INTO users(login,password,email) VALUES(?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    public boolean update(User user)
    {
        String sql = "UPDATE users set password=?,email=? where login=?";
        Boolean retorno = false;
        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {
          
            pst.setString(1, user.getPassword());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getLogin());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    public void delete(User user)
    {
        String sql = "DELETE FROM users where login=?";
        Boolean retorno = false;
        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {
          
           
            pst.setString(1, user.getLogin());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
    
    }
    
    public List<User> list()
    {
         String sql = "SELECT * FROM users";
        List<User> retorno = new ArrayList<User>();
        
        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {
           
            
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                User item = new User();
                item.setLogin(res.getString("login"));
                item.setPassword(res.getString("password"));
                item.setEmail(res.getString("email"));              
                retorno.add(item);
            }
               
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }
    
    public boolean checkLogin(String login, String password) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM users WHERE login = ? and password = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {

                
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return check;

    }
    public User search(User user)
    {
         String sql = "SELECT * FROM users where login=?";
        User pdao = null;
        
        PreparedStatement pst = ConnectionFactory.getPreparedStatement(sql);
        try {
           
            pst.setString(1, user.getLogin());
            ResultSet res = pst.executeQuery();
            
            if(res.next())
            {
                pdao = new User();
                pdao.setLogin(res.getString("login"));
                pdao.setPassword(res.getString("password"));
                pdao.setEmail(res.getString("email"));                
                
            }
               
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return pdao;
    
    
    }


}
