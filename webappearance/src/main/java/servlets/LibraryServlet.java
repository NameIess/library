package servlets;

import command.ActionCommand;
import command.ActionFactory;
import command.Page;
import command.exception.ActionException;
import daofactory.DaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.RequestManager;

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
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = RequestManager.getActionName(request);
        ActionCommand command = factory.defineCommand(actionName);
        String page = null;
        try {
            page = command.execute(request);
        } catch (ActionException e) {
            request.setAttribute("message", e.getMessage());
            String forwardErrorPage = Page.FORWARD.toString() + Page.ERROR.toString();
            forwardErrorPage = buildUrl(forwardErrorPage);
            forward(request, response, forwardErrorPage);
        }

        Log.debug("Page has been received from command: " + page);
        if (page != null && page.startsWith(Page.REDIRECT.toString())) {
            redirect(page, response);

        } else if (page != null && page.startsWith(Page.FORWARD.toString())) {
            page = buildUrl(page);
            forward(request, response, page);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        Log.debug("Forward to: " + page);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void redirect(String page, HttpServletResponse response) throws IOException {
        String contextPath = getServletContext().getContextPath();
        String redirectPageUrl = contextPath + Page.CHANGE_PAGE + page.substring(Page.REDIRECT.toString().length());
        response.sendRedirect(redirectPageUrl);
        Log.debug("Redirect to: " + redirectPageUrl);
    }

    private String buildUrl(String page) {
        Log.debug("Current page before building: " + page);
        String url = page.substring(Page.FORWARD.toString().length());
        String path = Page.VIEWS_PATH + url + Page.JSP_EXTENSION;
        Log.debug("Full path: " + path);
        return path;
    }

}
