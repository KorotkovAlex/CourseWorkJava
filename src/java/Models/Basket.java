/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Саня
 */
public class Basket {
    private int id;
    private Product product;
    private Date date;
    private String loginCus;

    /**
     * @return the products
     */
    

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the loginCus
     */
    public String getLoginCus() {
        return loginCus;
    }

    /**
     * @param loginCus the loginCus to set
     */
    public void setLoginCus(String loginCus) {
        this.loginCus = loginCus;
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
