package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.OptionalProduct;
import it.polimi.db2_project.ejb.beans.ServicePackage;
import it.polimi.db2_project.ejb.services.PackageService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/GoToBuyServices")
public class GoToBuyServices extends AbstractThymeleafServlet{
    @EJB(name = "it.polimi.db2_project.ejb.services.PackageService")
    private PackageService packageService;

    public GoToBuyServices() {
        super("buyServices", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> attributes = new HashMap<>();
        List<ServicePackage> servicePackages = packageService.findAllServicePackage();
        attributes.put("servicePackages",servicePackages);
        processTemplate(request,response,attributes);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }
}
