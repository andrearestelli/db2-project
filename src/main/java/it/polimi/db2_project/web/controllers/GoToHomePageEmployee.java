package it.polimi.db2_project.web.controllers;

import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/GoToHomePageEmployee")
public class GoToHomePageEmployee extends AbstractThymeleafServlet{

    public GoToHomePageEmployee() {
        super("homepageEmployee", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> attributes = new HashMap<>();
        attributes.put("username",(String) request.getSession().getAttribute("username"));
        processTemplate(request, response,attributes);
    }
}
