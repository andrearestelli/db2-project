package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.beans.ServicePackage;
import it.polimi.db2_project.ejb.services.CustomerService;
import it.polimi.db2_project.ejb.services.PackageService;
import it.polimi.db2_project.web.utils.UnconfirmedOrder;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/GoToConfirmationPage")
public class GoToConfirmationPage extends AbstractThymeleafServlet {
    @EJB(name = "it.polimi.db2_project.ejb.services.PackageService")
    private PackageService packageService;

    public GoToConfirmationPage() {
        super("confirmationPage","WEB-INF/templates/",".html",TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UnconfirmedOrder unconfirmedOrder = (UnconfirmedOrder) request.getSession().getAttribute("unconfirmedOrder");
        Map<String,Object> attributes = new HashMap<>();
        ServicePackage servicePackage = packageService.findByID(unconfirmedOrder.getServicePackageID());
        attributes.put("servicePackageID",servicePackage.getID());
        attributes.put("validity_period",servicePackage.getValidity_period());
        attributes.put("servicePackageName",servicePackage.getName());
        attributes.put("monthlyFee",servicePackage.getMonthly_fee());
        attributes.put("optionalProducts",unconfirmedOrder.getOptionalProductList());
        attributes.put("subscriptionDate",unconfirmedOrder.getSubscriptionDate());
        attributes.put("endingDate",unconfirmedOrder.computeEndingDate(unconfirmedOrder.getSubscriptionDate(),servicePackage.getValidity_period()));
        processTemplate(request,response,attributes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
