package it.polimi.db2_project.web.controllers;

import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/GoToHomePageCustomer")
public class GoToHomePageCustomer extends AbstractThymeleafServlet{

    public GoToHomePageCustomer() {
        super("homepageCustomer", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> attributes = new HashMap<>();
        attributes.put("username",(String) request.getSession().getAttribute("username"));
        attributes.put("mail",(String) request.getSession().getAttribute("mail"));
        processTemplate(request, response,attributes);
    }
}
