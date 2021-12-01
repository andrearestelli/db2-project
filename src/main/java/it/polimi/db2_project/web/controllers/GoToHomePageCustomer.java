package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.ServicePackage;
import it.polimi.db2_project.ejb.services.PackageService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/GoToHomePageCustomer")
public class GoToHomePageCustomer extends AbstractThymeleafServlet{

    @EJB(name = "it.polimi.db2_project.ejb.services.PackageService")
    private PackageService packageService;

    public GoToHomePageCustomer() {
        super("homepageCustomer", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> attributes = new HashMap<>();
        if(request.getSession().getAttribute("username")!=null){
            attributes.put("username", (String) request.getSession().getAttribute("username"));
            attributes.put("mail", (String) request.getSession().getAttribute("mail"));
        }

        List<ServicePackage> servicePackages = packageService.findAllServicePackage();
        attributes.put("servicePackages",servicePackages);
        processTemplate(request, response, attributes);
    }
}
