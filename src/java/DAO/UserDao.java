/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Role;
import Models.User;
//Получить пользователя
//
//
//
//
//
//

public class UserDao {
    
    ArrayList<User> users;    
    private  Connection con;
    private  PreparedStatement pstmt;
    private  ResultSet rs;
    
    public UserDao(Connection con){
        this.con = con;
    }

    public void insertUser(User user) throws SQLException, IOException {        
        pstmt = con.prepareStatement("insert User(login,password) values(?,?)");
        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, user.getPassword());        
        pstmt.executeUpdate();
    }
   
    public ArrayList<User> getAllUsers() throws SQLException, IOException {        
        users = new ArrayList<User>();       
        String query = "select * from user, role where user.login = role.login";
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            User user = new User();
            user.setLogin(rs.getString(2));
            users.add(user);
        }
        return users;        
    }
    
    public User getUser(String name) throws SQLException {
        User user = new User();
        String query = "select * from User where login = ?";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);        
        rs = pstmt.executeQuery();
        while (rs.next()) {
            user.setLogin(rs.getString(1));
            user.setPassword(rs.getString(2));
        }
        return user;
    }
    public void updateUser(User user, String name) throws SQLException, IOException  {
        pstmt = con.prepareStatement("UPDATE basket set login = ? WHERE login = ?;");
        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, name);
        pstmt.executeUpdate();
        pstmt = con.prepareStatement("UPDATE role set login = ? WHERE login = ?;");
        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, name);
        pstmt.executeUpdate();
        pstmt = con.prepareStatement("UPDATE user set login = ? WHERE login = ?;");
        pstmt.setString(1, user.getLogin());
        pstmt.setString(2, name);
        pstmt.executeUpdate();
    }

    public void deleteUserInBasketInRole(User user) throws SQLException, IOException {
        pstmt = con.prepareStatement("DELETE FROM basket WHERE login = ?;");
        pstmt.setString(1, user.getLogin());
        pstmt.executeUpdate();
        pstmt = con.prepareStatement("DELETE FROM role WHERE login = ?;");
        pstmt.setString(1, user.getLogin());
        pstmt.executeUpdate();
        pstmt = con.prepareStatement("DELETE FROM user WHERE login = ?;");
        pstmt.setString(1, user.getLogin());
        pstmt.executeUpdate();
    }    
}
