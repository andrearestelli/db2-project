package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.OptionalProduct;
import it.polimi.db2_project.ejb.services.CustomerService;
import it.polimi.db2_project.ejb.services.EmployeeService;
import it.polimi.db2_project.ejb.services.OptionalProductService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/GoToHomePageEmployee")
public class GoToHomePageEmployee extends AbstractThymeleafServlet{
    @EJB(name = "it.polimi.db2_project.ejb.services.EmployeeService")
    private EmployeeService employeeService;
    @EJB(name = "it.polimi.db2_project.ejb.services.OptionalProductService")
    private OptionalProductService optionalProductService;

    public GoToHomePageEmployee() {
        super("homepageEmployee", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("username", (String) request.getSession().getAttribute("username"));
        attributes.put("services",employeeService.findAllServices());
        attributes.put("optionalProducts",optionalProductService.findAllOptionalProduct());
        processTemplate(request, response, attributes);
    }
}
