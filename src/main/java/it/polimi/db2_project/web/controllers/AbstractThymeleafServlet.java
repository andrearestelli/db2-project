package it.polimi.db2_project.web.controllers;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractThymeleafServlet extends HttpServlet {
    protected String templatePath;
    private final String pathPrefix;
    private final String pathSuffix;
    protected TemplateMode templateMode;
    protected TemplateEngine templateEngine;

    public AbstractThymeleafServlet(String templatePath, String pathPrefix, String pathSuffix, TemplateMode templateMode) {
        this.templatePath = templatePath;
        this.pathPrefix = pathPrefix;
        this.pathSuffix = pathSuffix;
        this.templateMode = templateMode;
        this.templateEngine = new TemplateEngine();
    }

    public void init() {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver(servletContext);

        servletContextTemplateResolver.setTemplateMode(templateMode);
        servletContextTemplateResolver.setSuffix(pathSuffix);
        templateEngine.setTemplateResolver(servletContextTemplateResolver);
        servletContextTemplateResolver.setPrefix(pathPrefix);
    }

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate(request, response, null);
    }

    protected void processTemplate(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> variables) throws IOException {
        ServletContext servletCtx = getServletContext();

        final WebContext webContext = new WebContext(request, response, servletCtx, request.getLocale());
        if (variables != null) {
            webContext.setVariables(variables);
        }
        templateEngine.process(templatePath, webContext, response.getWriter());
    }

}
