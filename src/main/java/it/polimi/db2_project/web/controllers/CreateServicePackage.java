package it.polimi.db2_project.web.controllers;

import com.google.gson.Gson;
import it.polimi.db2_project.web.utils.RawServicePackage;
import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CreateServicePackage extends AbstractThymeleafServlet{

    public CreateServicePackage(String templatePath, String pathPrefix, String pathSuffix, TemplateMode templateMode) {
        super("homepageEmployee", "WEB_INF/templates/", ".html", TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RawServicePackage rawServicePackage;

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        rawServicePackage = gson.fromJson(reader, RawServicePackage.class);
    }
}
