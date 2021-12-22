package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Alert;
import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.Order;
import it.polimi.db2_project.ejb.services.EmployeeService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/GoToSalesReport")
public class GoToSalesReport extends AbstractThymeleafServlet{
    @EJB(name = "it.polimi.db2_project.ejb.services.EmployeeService")
    private EmployeeService employeeService;

    public GoToSalesReport() {
        super("salesReportPage", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("insolventCustomers", employeeService.findInsolventCustomers());
        attributes.put("rejectedOrders", employeeService.findAllRejectedOrders());
        attributes.put("alerts", employeeService.findAllAlerts());


        processTemplate(request, response, attributes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
