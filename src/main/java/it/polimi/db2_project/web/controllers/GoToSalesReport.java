package it.polimi.db2_project.web.controllers;

import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoToSalesReport")
public class GoToSalesReport extends AbstractThymeleafServlet{

    public GoToSalesReport() {
        super("salesReportPage", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
