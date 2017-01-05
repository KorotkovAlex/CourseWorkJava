/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import DAO.DepartmentDao;
import DAO.ProductDao;
import Models.Department;
import Models.Product;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author alex
 */
@ManagedBean(name="product")
@SessionScoped
public class ProductMB {

    private ArrayList<Product> products;
    private PreparedStatement pstmt;
    private String search;
    InitialContext ctx;
    DataSource ds;
    Connection con;
    Statement stmt;
    ResultSet rs;
    private Product product;
    ProductDao productDao;
    @PostConstruct
    public void init() {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mall");
            con = ds.getConnection();
            product = new Product();
            product.setDepartment(new Department());
            productDao = new ProductDao(con);
        } catch (NamingException ex) {
           
        } catch (SQLException ex) {
            
        }
    }
    
    public ProductMB() {
    }
    
    
    
    public ArrayList<Product> getAllProducts() throws SQLException, IOException, NamingException{   
        return productDao.getAllProducts();            
    }

    public void updateProduct() throws SQLException, IOException  {
        productDao.updateProduct(product);
    }   
    
    public void insertProduct() throws SQLException, IOException {        
        productDao.insertProduct(product);
    }

    /**
     * @return the products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public void deleteProduct(Product product) throws SQLException, IOException{
        productDao.deleteProduct(product);
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }
}
