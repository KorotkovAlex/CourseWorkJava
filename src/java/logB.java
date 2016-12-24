/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alex
 */
@ManagedBean
@SessionScoped
public class logB {

    /**
     * Creates a new instance of logB
     */
    public logB() {
    }
    public String logout() throws IOException{
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        HttpSession session = (HttpSession)ectx.getSession(false);
        session.invalidate();    
        response.sendRedirect("index.xhtml");
        return null;
    }
}
