/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author alex
 */
public class Role {
    
    private User user;
    private String role;
    
    public User getUser(){
        return this.user;
    }
    
    public void setUser(User user){
        this.user =user;
    }
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

}
