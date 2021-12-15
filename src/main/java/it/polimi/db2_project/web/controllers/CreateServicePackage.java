package it.polimi.db2_project.web.controllers;

import com.google.gson.Gson;
import it.polimi.db2_project.ejb.beans.OptionalProduct;
import it.polimi.db2_project.ejb.beans.Service;
import it.polimi.db2_project.ejb.services.EmployeeService;
import it.polimi.db2_project.ejb.services.OptionalProductService;
import it.polimi.db2_project.ejb.services.PackageService;
import it.polimi.db2_project.web.utils.RawServicePackage;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/CreateServicePackage")
@MultipartConfig
public class CreateServicePackage extends AbstractThymeleafServlet{
    @EJB(name = "it.polimi.db2_project.ejb.services.EmployeeService")
    private EmployeeService employeeService;
    @EJB(name = "it.polimi.db2_project.ejb.services.OptionalProductService")
    private OptionalProductService optionalProductService;
    @EJB(name = "it.polimi.db2_project.ejb.services.PackageService")
    private PackageService packageService;

    public CreateServicePackage() {
        super("homepageEmployee", "WEB_INF/templates/", ".html", TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RawServicePackage rawServicePackage;

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        rawServicePackage = gson.fromJson(reader, RawServicePackage.class);

        List<Service> serviceList = employeeService.findServiceListByID(rawServicePackage.getServices());
        List<OptionalProduct> optionalProductList = optionalProductService.
                findOptionalProductListByID(rawServicePackage.getOptionalProducts());

        packageService.createServicePackage(rawServicePackage.getName(), rawServicePackage.getValidityPeriod(),
                rawServicePackage.getMonthly_fee(), serviceList, optionalProductList);

        response.setStatus(HttpServletResponse.SC_OK);

    }
}
