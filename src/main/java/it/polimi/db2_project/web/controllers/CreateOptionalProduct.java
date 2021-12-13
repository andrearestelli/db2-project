package it.polimi.db2_project.web.controllers;

import it.polimi.db2_project.ejb.services.OptionalProductService;
import it.polimi.db2_project.web.utils.ParametersChecker;
import org.thymeleaf.templatemode.TemplateMode;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Stream;

@WebServlet("/CreateOptionalProduct")
public class CreateOptionalProduct extends AbstractThymeleafServlet {
    @EJB(name = "it.polimi.db2_project.ejb.services.OptionalProductService")
    private OptionalProductService optionalProductService;

    public CreateOptionalProduct() {
        super("homePageEmployee", "WEB_INF/templates/", ".html", TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer validityPeriod = Integer.parseInt(request.getParameter("validity_period"));
        Double monthlyFee = Double.parseDouble(request.getParameter("monthly_fee"));

        if(ParametersChecker.discoverInvalidParameters(Stream.of(name)) && validityPeriod <= 0
                && monthlyFee <= 0.0){
            processTemplate(request, response, Collections.singletonMap("errorMessage",
                    "Invalid parameters"));
            return;
        }

        optionalProductService.createOptionalProduct(name, validityPeriod, monthlyFee);
        response.sendRedirect(getServletContext().getContextPath() + "/GoToHomePageEmployee");
    }
}
