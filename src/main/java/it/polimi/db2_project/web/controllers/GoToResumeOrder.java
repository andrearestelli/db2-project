package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.Order;
import it.polimi.db2_project.ejb.services.OrderService;
import it.polimi.db2_project.web.utils.UnconfirmedOrder;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.persistence.Id;
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
        Integer IDorder =Integer.parseInt(request.getParameter("IDorder"));
        Order order = orderService.findByID(IDorder);
        UnconfirmedOrder unconfirmedOrder = new UnconfirmedOrder(order.getId_package(),
                order.getOptionalProducts(),
                order.getSub_date());
        unconfirmedOrder.computeTotalPrice();
        request.getSession().setAttribute("unconfirmedOrder",unconfirmedOrder);
        response.sendRedirect(getServletContext().getContextPath()+"/GoToConfirmationPage?orderID="+IDorder);
    }
}
