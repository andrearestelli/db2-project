package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.services.CustomerService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB(name = "it.polimi.db2_project.ejb.services.CustomerService")
    private CustomerService customerService;

    public CheckLogin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String username = null;
        String password = null;
        username = request.getParameter("username");
        password = request.getParameter("password");
        if(username == null || password == null || username.isEmpty() || password.isEmpty()) {
            //TODO ritorna errore
        }
        Optional<Customer> result = customerService.checkCredentials(username,password);
        if(result.isPresent()){
            Customer customer = result.get();
            request.getSession().setAttribute("user",customer);
            //TODO Manda alla Homepage
        }
        else{
            //TODO Ritorna errore
        }
    }

    public void destroy(){

    }
}
