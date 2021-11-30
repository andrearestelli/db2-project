package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.services.CustomerService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/CheckLoginCustomer")
public class CheckLoginCustomer extends AbstractThymeleafServlet {
    private static final long serialVersionUID = 1L;
    @EJB(name = "it.polimi.db2_project.ejb.services.CustomerService")
    private CustomerService customerService;

    public CheckLoginCustomer() {
        super("customerLogin","WEB-INF/templates/",".html",TemplateMode.HTML);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        processTemplate(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            request.getSession().setAttribute("username",customer.getUsername());
            request.getSession().setAttribute("mail",customer.getMail());
            //Manda alla Homepage
            response.sendRedirect(getServletContext().getContextPath()+"/GoToHomePageCustomer");
        }
        else{
            //TODO Ritorna errore
        }
    }

    public void destroy(){

    }
}
