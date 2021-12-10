package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.ServicePackage;
import it.polimi.db2_project.ejb.services.CustomerService;
import it.polimi.db2_project.ejb.services.OrderService;
import it.polimi.db2_project.ejb.services.PackageService;
import it.polimi.db2_project.web.utils.DateHandler;
import it.polimi.db2_project.web.utils.UnconfirmedOrder;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/GoToConfirmationPage")
public class GoToConfirmationPage extends AbstractThymeleafServlet {
    @EJB(name = "it.polimi.db2_project.ejb.services.PackageService")
    private PackageService packageService;
    @EJB(name = "it.polimi.db2_project.ejb.services.OrderService")
    private OrderService orderService;

    public GoToConfirmationPage() {
        super("confirmationPage","WEB-INF/templates/",".html",TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UnconfirmedOrder unconfirmedOrder = (UnconfirmedOrder) request.getSession().getAttribute("unconfirmedOrder");
        Map<String,Object> attributes = new HashMap<>();
        boolean logged = request.getSession().getAttribute("user") != null;
        attributes.put("logged",logged);
        attributes.put("servicePackage",unconfirmedOrder.getServicePackage());
        attributes.put("optionalProducts",unconfirmedOrder.getOptionalProductList());
        attributes.put("subscriptionDate",unconfirmedOrder.getSubscriptionDate());
        attributes.put("endingDate", DateHandler.computeEndingDate(unconfirmedOrder.getSubscriptionDate(),unconfirmedOrder.getServicePackage().getValidity_period()));
        attributes.put("totalPrice", unconfirmedOrder.getTotalPrice());
        processTemplate(request,response,attributes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UnconfirmedOrder unconfirmedOrder = (UnconfirmedOrder) request.getSession().getAttribute("unconfirmedOrder");
        int orderID = orderService.createOrder(new Date(),50,unconfirmedOrder.getSubscriptionDate(),
                (Customer) request.getSession().getAttribute("user"),
                unconfirmedOrder.getServicePackage());
        response.sendRedirect(getServletContext().getContextPath()+"/GoToServicePayment?"+"orderID="+orderID);
    }
}
