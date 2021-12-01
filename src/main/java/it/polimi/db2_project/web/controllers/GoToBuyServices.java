package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/GoToBuyServices")
public class GoToBuyServices extends AbstractThymeleafServlet{

    public GoToBuyServices() {
        super("buyServices", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }
}
