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
import Models.Department;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author alex
 */
public class DepartmentDao {
//Получитчь все категории 
    ArrayList<Department> departments;
    
    private  Connection con;
    private  PreparedStatement pstmt;
    private  ResultSet rs;
    InitialContext ctx;
    DataSource ds;
    Statement stmt;

    public DepartmentDao(Connection con){
        this.con = con;
    }
    
    public void insertDepartment(Department department) throws SQLException, IOException {        
        pstmt = con.prepareStatement("insert admin(name) values(?)");
        pstmt.setString(1, department.getName());        
        pstmt.executeUpdate();
    }
   
    public ArrayList<Department> getAllDepartments() throws SQLException, IOException {        
        departments = new ArrayList<Department>();       
        String query = "select * from Department1";
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Department department = new Department();
            department.setIdDepartment(rs.getInt(1));
            department.setName(rs.getString(2));
            departments.add(department);
        }
        return departments;        
    }
    
    public Department getDepartment(int i) throws SQLException, IOException {           
        String query = "select * from Department1 where idDepartment = " + i;
        pstmt = con.prepareStatement(query);
        //pstmt.setInt(1, i); 
        rs = pstmt.executeQuery();       
        Department department = new Department();
        department.setIdDepartment(rs.getLong("idDepartment"));
        department.setName(rs.getString("name"));           
        return department;        
    }
    public void updateDepartments(Department department, String name) throws SQLException, IOException  {
        pstmt = con.prepareStatement("UPDATE Department1 set name = ? WHERE id = ?;");
        pstmt.setString(1, name);
        pstmt.setInt(2, (int) department.getIdDepartment());
        pstmt.executeUpdate();
    }

    public void deleteDepartment(Department department) throws SQLException, IOException {
        pstmt = con.prepareStatement("DELETE FROM admin WHERE id = ?;");
        pstmt.setInt(1, (int) department.getIdDepartment());
        pstmt.executeUpdate();
    }    
}
