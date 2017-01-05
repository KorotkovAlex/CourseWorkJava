/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import DAO.BasketDao;
import DAO.ProductDao;
import Models.Basket;
import Models.Department;
import Models.Product;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

/**
 *
 * @author Саня
 */
@ManagedBean(name="basket")
@SessionScoped
public class BasketMB {
    
    private PreparedStatement pstmt;
    InitialContext ctx;
    DataSource ds;
    Connection con;
    Statement stmt;
    ResultSet rs;
    BasketDao basketDao;
    @PostConstruct
    public void init() {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mall");
            con = ds.getConnection();
            basketDao = new BasketDao(con);             
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        }
    }
    public BasketMB() {
    }
    
    public ArrayList<Basket> getAllProductsFromBasket() throws SQLException, IOException, NamingException{ 
    return basketDao.getAllProductsFromBasket();       
    }
    
    public HashMap<Date, ArrayList<Basket>> getListOfLists(String login) throws SQLException, IOException{
        ArrayList<Basket> baskets1 = new ArrayList<>();
        ArrayList<Basket> baskets2 = new ArrayList<>();
        Date date;
        baskets1 = basketDao.getAllProductsFromBasket();
        HashMap map = new HashMap<Date,ArrayList<Basket>>();
        for(Basket basket1 : baskets1){
            basket1.getDate();
            if (map.get(basket1.getDate())== null && basket1.getLoginCus().equals(login)) {
                date = basket1.getDate();
                for(Basket basket2 : baskets1){
                    if (basket2.getDate().equals(date)) {
                        baskets2.add(basket2);
                    }
                }
                map.put(basket1.getDate(), baskets2);
                baskets2 = new ArrayList<>();
            }
        }
        return map;
    } 
    public void addProductInBasket(Product product, String login) throws SQLException{
        basketDao.addProductInBasket(product, login);
    }
    
    public void removePrductFromBasket(Basket basket) throws SQLException{
        basketDao.removePrductFromBasket(basket);
    }
}
