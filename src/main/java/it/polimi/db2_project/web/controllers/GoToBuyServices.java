package it.polimi.db2_project.web.controllers;

import com.google.gson.Gson;
import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.OptionalProduct;
import it.polimi.db2_project.ejb.beans.ServicePackage;
import it.polimi.db2_project.ejb.services.PackageService;
import it.polimi.db2_project.web.utils.OptionalProductList;
import it.polimi.db2_project.web.utils.UnconfirmedOrder;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        UnconfirmedOrder unconfirmedOrder;

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();

        unconfirmedOrder = gson.fromJson(reader, UnconfirmedOrder.class);

        // Controllare se i prodotti opzionali selezionati sono coerenti ???

        request.getSession().setAttribute("unconfirmedOrder",unconfirmedOrder);
        response.sendRedirect(getServletContext().getContextPath()+"/GoToConfirmationPage");

    }
}
