package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Customer;
import it.polimi.db2_project.ejb.beans.Order;
import it.polimi.db2_project.ejb.beans.ServiceActivationSchedule;
import it.polimi.db2_project.ejb.services.CustomerService;
import it.polimi.db2_project.ejb.services.OrderService;
import it.polimi.db2_project.ejb.services.PackageService;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

@WebServlet("/GoToServicePayment")
public class GoToServicePayment extends AbstractThymeleafServlet{
    @EJB(name = "it.polimi.db2_project.ejb.services.OrderService")
    private OrderService orderService;
    @EJB(name = "it.polimi.db2_project.ejb.services.CustomerService")
    private CustomerService customerService;

    public GoToServicePayment() {
        super("paymentResultPage","WEB-INF/templates/",".html",TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer orderID = Integer.parseInt(request.getParameter("orderID"));
        Random rn = new Random();
        int external_service = rn.nextInt(3);
        Order.StateType stateType;
        if (external_service == 2) {
            stateType = Order.StateType.REJECTED;
            customerService.setInsolventTrue(((Customer) request.getSession().getAttribute("user")).getUsername());
        }
        else {
            stateType = Order.StateType.VALID;
            orderService.createActivationSchedule((Customer) request.getSession().getAttribute("user"),
                    orderService.findByID(orderID));
            //TODO inviare la lista degli optional products selezionati
        }

        // Update state of order in db
        orderService.setStateByID(orderID, stateType);

        // Remove unconfirmed order from the session
        request.getSession().removeAttribute("unconfirmedOrder");

        // Append to the template a boolean variable, true if the payment has been accepted, false otherwise
        processTemplate(request,response, Collections.singletonMap("orderOutcome", stateType == Order.StateType.VALID));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

    }

}
