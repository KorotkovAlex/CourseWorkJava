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

/**
 *
 * @author alex
 */
public class RoleDao {
//Получить роль
    ArrayList<Role> roles;    
    private  Connection con;
    private  PreparedStatement pstmt;
    private  ResultSet rs;
    
    public RoleDao(Connection con){
        this.con = con;
    }

    public void insertRole(Role role) throws SQLException, IOException {        
        pstmt = con.prepareStatement("insert Role values(?,?)");
        pstmt.setString(1, role.getUser().getLogin());
        pstmt.setString(2, role.getRole());        
        pstmt.executeUpdate();
    }
   
    public ArrayList<Role> getAllRoles() throws SQLException, IOException {        
        roles = new ArrayList<Role>();       
        String query = "select role.login, role.role, user.password from role, user where user.login = role.login";
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Role role = new Role();
            role.setRole(rs.getString(1));
            User user = new User();
            user.setLogin(rs.getString(2));
            user.setPassword(rs.getString(3));
            role.setUser(user);
            roles.add(role);
        }
        return roles;        
    }
    
    public void updateRole(Role role) throws SQLException, IOException  {
        pstmt = con.prepareStatement("UPDATE role set role = ? WHERE login = ?;");
        pstmt.setString(1, role.getRole());
        pstmt.setString(2, role.getUser().getLogin());
        pstmt.executeUpdate();
    }

    public void deleteRole(Role role) throws SQLException, IOException {
        pstmt = con.prepareStatement("DELETE FROM role WHERE login = ?;");
        pstmt.setString(1, role.getUser().getLogin());
        pstmt.executeUpdate();
    }    
}
