package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.services.CustomerService;
import it.polimi.db2_project.web.utils.ParametersChecker;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

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
        String username;
        String password;
        username = request.getParameter("username");
        password = request.getParameter("password");

        if(ParametersChecker.discoverInvalidParameters(Stream.of(username, password))) {
            processTemplate(request, response, Collections.singletonMap("errorMessage",
                    "Invalid parameters"));
            return;
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
            // ritorna messaggio di errore
            processTemplate(request, response, Collections.singletonMap("errorMessage",
                    "Invalid username or password"));
        }
    }

    public void destroy(){

    }
}
