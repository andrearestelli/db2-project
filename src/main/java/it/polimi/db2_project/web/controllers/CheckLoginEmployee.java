package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.Employee;
import it.polimi.db2_project.ejb.services.CustomerService;
import it.polimi.db2_project.ejb.services.EmployeeService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/CheckLoginEmployee")
public class CheckLoginEmployee extends AbstractThymeleafServlet{
    private static final long serialVersionUID = 1L;
    @EJB(name = "it.polimi.db2_project.ejb.services.EmployeeService")
    private EmployeeService employeeService;

    public CheckLoginEmployee() {
        super("employeeLogin","WEB-INF/templates/",".html",TemplateMode.HTML);
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
        Optional<Employee> result = employeeService.checkCredentials(username,password);
        if(result.isPresent()){
            Employee employee = result.get();
            request.getSession().setAttribute("user",employee);
            //TODO Manda alla Homepage
            request.getSession().setAttribute("username",employee.getUsername());
            //Manda alla Homepage
            response.sendRedirect(getServletContext().getContextPath()+"/GoToHomePageEmployee");
        }
        else{
            //TODO Ritorna errore
        }
    }

    public void destroy(){

    }
}
