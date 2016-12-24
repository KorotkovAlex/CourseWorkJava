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
public class Department {
    
    private long idDepartment;
    private String name;

    public long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
