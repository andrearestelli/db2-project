package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Order;
import it.polimi.db2_project.ejb.services.OrderService;
import it.polimi.db2_project.web.utils.UnconfirmedOrder;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GoToResumeOrder")
public class GoToResumeOrder extends HttpServlet {
    @EJB(name = "it.polimi.db2_project.ejb.services.OrderService")
    private OrderService orderService;

    public GoToResumeOrder() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Order order = orderService.findByID(orderID);
        UnconfirmedOrder unconfirmedOrder = new UnconfirmedOrder(order.getId_package(),
                order.getOptionalProducts(),
                order.getSub_date());
        unconfirmedOrder.computeTotalPrice();
        request.getSession().setAttribute("unconfirmedOrder",unconfirmedOrder);
        response.sendRedirect(getServletContext().getContextPath()+"/GoToConfirmationPage?orderID="+ orderID);
    }
}
