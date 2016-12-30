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
    private long idProduct;
    private String name;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Department department;
    private int depInt;
    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public int getdepInt() {
        return depInt;
    }

    public void setdepInt(int depInt) {
        this.depInt = depInt;
    }
    
    private ArrayList<Product> products;
    private PreparedStatement pstmt;
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
            productDao = new ProductDao(con);
        } catch (NamingException ex) {
           
        } catch (SQLException ex) {
            
        }
    }
    
    public ProductMB() {
    }
    
    
    
    public ArrayList<Product> getAllProduct() throws SQLException, IOException, NamingException{   
        return productDao.getAllProducts();            
    }
    public void updateProduct(Product product, String name) throws SQLException, IOException  {
    
    }
    
    public void insertProduct() throws SQLException, IOException {        
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDiscountPrice(discountPrice);
        department = new Department();
        department.setIdDepartment(depInt);
        product.setDepartment(department);
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
}
