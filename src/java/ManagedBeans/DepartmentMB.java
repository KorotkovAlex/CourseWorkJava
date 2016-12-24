/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import DAO.DepartmentDao;
import Models.Department;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author alex
 */
@ManagedBean(name="depDao")
@SessionScoped
public class DepartmentMB {

    ArrayList<Department> departments;
    private PreparedStatement pstmt;
    private Department department;
    InitialContext ctx;
    DataSource ds;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DepartmentDao departmentDao;


    @PostConstruct
    public void init() {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mall");
            con = ds.getConnection();
            departmentDao = new DepartmentDao(con);
//			//add students
//			studentList.add(new Student(111, "Ram", "Ayodhya"));
//			studentList.add(new Student(222, "Mahesh", "Varanasi"));
//			studentList.add(new Student(333, "Krishna", "Mathura"));
        } catch (NamingException ex) {
           
        } catch (SQLException ex) {
            
        }
    }
    
    public DepartmentMB() {
    }

    public void insertDepartment(Department department) throws SQLException, IOException {
        departmentDao.insertDepartment(department);
    }
   
    public ArrayList<Department> getAllDepartments() throws SQLException, IOException {        
        departments = new ArrayList<Department>();
        return departmentDao.getAllDepartments();       
    }
    
    public void updateDepartments(Department department, String name) throws SQLException, IOException  {
        departmentDao.updateDepartments(department, name);
    }

    public void deleteDepartment(Department department) throws SQLException, IOException {
    }  
}
