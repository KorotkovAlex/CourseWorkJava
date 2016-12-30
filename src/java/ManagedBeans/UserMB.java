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

    private String login;
    private String password;
    private String roleName;
    private InitialContext ctx;
    private DataSource ds;
    private Connection con;
    UserDao userDao;
    RoleDao roleDao;
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public UserMB() {
    }
    @PostConstruct
    public void init() {
       userDao = new UserDao(connection());
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
    
    public void insertUser() throws SQLException, IOException {        
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        Role role = new Role();
        role.setLogin(login);
        role.setRole(roleName);    
        userDao.insertUser(user);
        con.close();
        roleDao = new RoleDao(connection());
        roleDao.insertRole(role);
        con.close();        
    }
}
