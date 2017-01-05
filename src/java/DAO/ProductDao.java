package DAO;

import Models.Department;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Product;
import javax.naming.NamingException;

/**
 *
 * @author alex
 */
public class ProductDao {    
    //Получить список товаров   
//Получить самые дешвые товары в категориях
//Посмотреть список товаров со скидкой
//Оценить товар
//Посмотреть лучшие товары поствить поле best, чтоб выборку не делать 
//Сосздать свой список 
//Удалить твоар
//Создать товр
//Изменить товар    
    ArrayList<Product> products;    
    private  Connection con;
    private  PreparedStatement pstmt;
    private  ResultSet rs;
    
    public ProductDao(Connection con){
        this.con = con;
    }
    
    public ArrayList<Product> getAllProducts() throws SQLException, IOException,
            NamingException {     
        products = new ArrayList<Product>();       
        String query = "select d.idDepartment, d.name, p.idProduct, p.name, "
                + "p.price, p.discountPrice from department1 as d, product as p"
                + " where d.idDepartment = p.idDepartment";
        pstmt = con.prepareStatement(query);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            Department department = new Department();
            department.setIdDepartment(rs.getInt(1));
            department.setName(rs.getString(2));
            product.setDepartment(department);
            product.setIdProduct(rs.getInt(3));
            product.setName(rs.getString(4));
            product.setPrice(rs.getBigDecimal(5));
            product.setDiscountPrice(rs.getBigDecimal(6));
            products.add(product);
        }
        return products;        
    }    
    
    public void deleteProduct(Product product) throws SQLException, IOException {
        pstmt = con.prepareStatement("DELETE FROM product WHERE idProduct = ?;");
        pstmt.setInt(1, (int)product.getIdProduct());
        pstmt.executeUpdate();
    }

    public void insertProduct(Product product) throws SQLException, IOException {        
        pstmt = con.prepareStatement("insert product(name,price,idDepartment,"
                + "discountPrice) values(?,?,?,?)");
        pstmt.setString(1, product.getName());
        pstmt.setBigDecimal(2, product.getPrice());
        pstmt.setLong(3, product.getDepartment().getIdDepartment()); 
        pstmt.setBigDecimal(4, product.getDiscountPrice()); 
        pstmt.executeUpdate();
    }
    
    public void updateProduct(Product product) throws SQLException, IOException  {
        pstmt = con.prepareStatement("UPDATE product set name = ? , price = ? , idDepartment = ? , discountPrice = ? WHERE idProduct = ?;");
        pstmt.setString(1, product.getName());
        pstmt.setBigDecimal(2, product.getPrice());
        pstmt.setLong(3, product.getDepartment().getIdDepartment());
        pstmt.setBigDecimal(4, product.getDiscountPrice());
        pstmt.setLong(5, product.getIdProduct());
        pstmt.executeUpdate();
        //update product set name
    }        
}