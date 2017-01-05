/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;
import DAO.RoleDao;
import DAO.UserDao;
import Models.Department;
import Models.Product;
import Models.Role;
import Models.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Саня
 */
@ManagedBean(name="userMB")
@SessionScoped
public class UserMB {

    
    private InitialContext ctx;
    private DataSource ds;
    private Connection con;
    private Role role;
    UserDao userDao;
    RoleDao roleDao;
 
    public UserMB() {
    }
    @PostConstruct
    public void init() {
       userDao = new UserDao(connection());
       roleDao = new RoleDao(connection());
       role = new Role();
       role.setUser(new User());
    }
    public ArrayList<Role> getAllUsers() throws SQLException, IOException{
        // Получаю список пользователь из класса роль потомучто так связанно бд 

        return roleDao.getAllRoles();
        
    }
    public Connection connection(){
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mall");
            con = ds.getConnection();
            
        } catch (NamingException ex) {
           
        } catch (SQLException ex) {            
        }
        return con;
    }
    public User getUser(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        User user = (User)session.getAttribute("current_user");
        return user;
    }
    

    
    public void userForUpdate(Role role) {
        this.role = role;
    }
    
    public void updateUser(String oldLogin) throws SQLException, IOException {
        userDao.updateUser(getRole().getUser(), oldLogin);  
    }
    public void insertUser() throws SQLException, IOException {  
        userDao.insertUser(role.getUser());
        con.close();     
        roleDao = new RoleDao(connection());
        roleDao.insertRole(role);
        con.close();        
    }
    
    public void removeUser(Role role) throws SQLException, IOException {
        userDao.deleteUserInBasketInRole(role.getUser());
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
