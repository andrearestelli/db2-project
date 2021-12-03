package it.polimi.db2_project.web.controllers;

import org.thymeleaf.templatemode.TemplateMode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/GoToConfirmationPage")
public class GoToConfirmationPage extends AbstractThymeleafServlet {
    public GoToConfirmationPage() {
        super("confirmationPage","WEB-INF/templates/",".html",TemplateMode.HTML);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
