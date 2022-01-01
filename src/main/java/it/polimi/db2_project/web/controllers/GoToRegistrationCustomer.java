package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.services.CustomerService;
import it.polimi.db2_project.ejb.services.PackageService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/GoToRegistrationCustomer")
public class GoToRegistrationCustomer extends AbstractThymeleafServlet{
    @EJB(name = "it.polimi.db2_project.ejb.services.CustomerService")
    private CustomerService customerService;

    public GoToRegistrationCustomer() {
        super("registrationCustomer", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        processTemplate(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username;
        String password;
        String mail;
        username = request.getParameter("username");
        password = request.getParameter("password");
        mail = request.getParameter("mail");
        if(username == null || password == null || username.isEmpty() || password.isEmpty() || mail == null || mail.isEmpty()) {
            //TODO ritorna errore
        }
        Optional<Customer> result = customerService.findCustomerByUsername(username);
        if(result.isPresent()) {
            //TODO messaggio di errore utente esistente
        }
        else{
            customerService.registerCustomer(username,password,mail);
            response.sendRedirect(request.getServletContext().getContextPath()+"/CheckLoginCustomer");
        }
    }
}
