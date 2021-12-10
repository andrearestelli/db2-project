package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.Order;
import it.polimi.db2_project.ejb.beans.ServicePackage;
import it.polimi.db2_project.ejb.services.OrderService;
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
    @EJB(name = "it.polimi.db2_project.ejb.services.OrderService")
    private OrderService orderService;

    public GoToHomePageCustomer() {
        super("homepageCustomer", "WEB-INF/templates/", ".html", TemplateMode.HTML);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> attributes = new HashMap<>();
        Customer customer = (Customer)request.getSession().getAttribute("user");
        if(customer!=null){
            attributes.put("username", customer.getUsername());
            attributes.put("mail", customer.getMail());
            System.out.println(customer.isInsolvent());
            attributes.put("insolvent",customer.isInsolvent());
            if(customer.isInsolvent()){
                List<Order> rejectedOrders = orderService.findRejectedOrdersByUser(customer);
                attributes.put("rejectedOrders",rejectedOrders);
            }

        }

        List<ServicePackage> servicePackages = packageService.findAllServicePackage();
        attributes.put("servicePackages",servicePackages);
        processTemplate(request, response, attributes);
    }
}
