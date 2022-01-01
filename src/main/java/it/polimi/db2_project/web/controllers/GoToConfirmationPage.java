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

        // Check if an unconfirmed order has been previously created
        if(unconfirmedOrder == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No orders found!");
            return;
        }

        int orderID;

        // If the argument of Integer.parseInt is null or is a string of length zero, a
        // NumberFormatException is thrown
        // @see https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html#parseInt(java.lang.String)
        try {
            orderID = Integer.parseInt(request.getParameter("orderID"));
        } catch (NumberFormatException | NullPointerException e ) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or missing parameters");
            return;
        }

        Map<String,Object> attributes = new HashMap<>();
        Customer user = (Customer) request.getSession().getAttribute("user");
        if(user != null){
            attributes.put("username", user.getUsername());
        }
        attributes.put("servicePackage",unconfirmedOrder.getServicePackage());
        attributes.put("optionalProducts",unconfirmedOrder.getOptionalProductList());
        attributes.put("subscriptionDate",unconfirmedOrder.getSubscriptionDate());
        attributes.put("endingDate", DateHandler.computeEndingDate(unconfirmedOrder.getSubscriptionDate(),unconfirmedOrder.getServicePackage().getValidity_period()));
        attributes.put("totalPrice", unconfirmedOrder.getTotalPrice());
        if(orderID != 0)
            attributes.put("orderID",orderID);
        processTemplate(request,response,attributes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UnconfirmedOrder unconfirmedOrder = (UnconfirmedOrder) request.getSession().getAttribute("unconfirmedOrder");
        Integer orderID;

        // Check if the customer is actually logged in
        if(request.getSession().getAttribute("user") != null){
            if(request.getParameter("orderID")!=null)
                orderID = Integer.parseInt(request.getParameter("orderID"));
            else orderID = orderService.createOrder(new Date(),unconfirmedOrder.getTotalPrice(),unconfirmedOrder.getSubscriptionDate(),
                    (Customer) request.getSession().getAttribute("user"),
                    unconfirmedOrder.getServicePackage(),unconfirmedOrder.getOptionalProductList());
            response.sendRedirect(getServletContext().getContextPath()+"/GoToServicePayment?"+"orderID="+orderID);
        } else {
            // If not logged in send him back to the login page
            response.sendRedirect(getServletContext().getContextPath()+"/CheckLoginCustomer");
        }

    }
}
