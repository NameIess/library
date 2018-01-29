package com.epam.training.library.viewlayer.servlets;


import com.epam.training.library.daolayer.connection.DbConnectionPool;
import com.epam.training.library.viewlayer.command.ActionCommand;
import com.epam.training.library.viewlayer.command.ActionFactory;
import com.epam.training.library.viewlayer.command.Message;
import com.epam.training.library.viewlayer.command.Page;
import com.epam.training.library.viewlayer.command.exception.ActionException;
import com.epam.training.library.daolayer.connection.exception.DbConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.training.library.viewlayer.util.RequestManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/libraryDispatcher", urlPatterns = {"/"})
public class LibraryServlet extends HttpServlet {
    private static final Logger Log = LogManager.getLogger(LibraryServlet.class.getSimpleName());
    private ActionFactory factory;

    @Override
    public void init() throws ServletException {
        super.init();
        factory = new ActionFactory();
    }

    @Override
    public void destroy() {
        super.destroy();
        DbConnectionPool pool = DbConnectionPool.getInstance();
        pool.releasePool();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = RequestManager.getActionName(request);
        ActionCommand command = factory.defineCommand(actionName);
        String page;
        try {
            page = command.execute(request);

            Log.debug("Page " + page + " has been received from command");
            if (page != null && page.startsWith(Page.REDIRECT.toString())) {
                redirect(page, response);
            } else if (page != null && page.startsWith(Page.FORWARD.toString())) {
                forward(request, response, page);
            } else {
                response.sendError(response.SC_BAD_REQUEST, Message.FATAL_ERROR.toString());
            }
        } catch (ActionException | DbConnectionPoolException e) {
            Log.error("Error during action execution. " + e.getMessage());
            response.sendError(response.SC_INTERNAL_SERVER_ERROR, Message.FATAL_ERROR.toString());
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        String forwardPage = buildUrl(page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardPage);
        dispatcher.forward(request, response);
        Log.debug("Forward to: " + forwardPage);
    }

    private void redirect(String page, HttpServletResponse response) throws IOException {
        String contextPath = getServletContext().getContextPath();
        String redirectPageUrl = contextPath + Page.CHANGE_PAGE + page.substring(Page.REDIRECT.toString().length());
        response.sendRedirect(redirectPageUrl);
        Log.debug("Redirect to: " + redirectPageUrl);
    }

    private String buildUrl(String page) {
        String url = page.substring(Page.FORWARD.toString().length());
        String path = Page.VIEWS_PATH + url + Page.JSP_EXTENSION;
        return path;
    }

}
