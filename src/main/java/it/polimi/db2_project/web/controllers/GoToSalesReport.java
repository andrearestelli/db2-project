package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.services.EmployeeService;
import it.polimi.db2_project.ejb.services.SalesReportService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/GoToSalesReport")
public class GoToSalesReport extends AbstractThymeleafServlet{
    @EJB(name = "it.polimi.db2_project.ejb.services.EmployeeService")
    private EmployeeService employeeService;
    @EJB(name = "it.polimi.db2_project.ejb.services.SalesReportService")
    private SalesReportService salesReportService;

    public GoToSalesReport() {
        super("salesReportPage", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("insolventCustomers", salesReportService.findInsolventCustomers());
        attributes.put("rejectedOrders", salesReportService.findAllRejectedOrders());
        attributes.put("alerts", salesReportService.findAllAlerts());
        attributes.put("averageNumberOfOptP",salesReportService.retrieveAllAverage());
        attributes.put("totalPurchasesPerPackage",salesReportService.retrieveAllTotalPurchasesPerPackage());
        attributes.put("TotalPurchasesValidityPeriod",salesReportService.retrieveAllTotalPurchasesValidityPeriod());
        attributes.put("totalValueOfSales",salesReportService.retrieveAllTotalValueOfSales());
        attributes.put("bestSellerOptionalProduct",salesReportService.retrieveBestSellerOptProduct());
        processTemplate(request, response, attributes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
