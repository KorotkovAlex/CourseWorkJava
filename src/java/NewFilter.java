/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DAO.DepartmentDao;
import DAO.UserDao;
import Models.Department;
import Models.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Саня
 */
public class NewFilter implements Filter {
    
    private static final boolean debug = true;
    private PreparedStatement pstmt;
    private Department department;
    InitialContext ctx;
    DataSource ds;
    Connection con;
    Statement stmt;
    ResultSet rs;
    DepartmentDao departmentDao;
    UserDao userDao;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public NewFilter() {
    }        
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mall");
            con = ds.getConnection();
            userDao = new UserDao(con);
        } catch (NamingException ex) {
            Logger.getLogger(NewFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(NewFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.sendRedirect("newjsp.jsp");   
        if (((HttpServletRequest) request).getSession().getAttribute("current_user") == null) {
            String username = ((HttpServletRequest) request).getRemoteUser(); 
            if (username != null) {        
                User user = new User();
                try {  
                    user = userDao.getUser(username);
                    ((HttpServletRequest) request).getSession()
                        .setAttribute("current_user", user);
                } catch (SQLException ex) {
                    Logger.getLogger(NewFilter.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }         
        }
        chain.doFilter(request, response); 
    }    
    @Override
    public void destroy() {      
        this.filterConfig = null;    
    }
           
}
