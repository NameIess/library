package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangePageCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(ChangePageCommand.class.getSimpleName());

    @Override
    public String execute(HttpServletRequest request) {
        String pageNameParameter = request.getParameter(Page.REQUEST_REDIRECT_PARAMETER.toString());
        String pageName = pageNameParameter.toUpperCase();
        String path = Page.FORWARD.toString() + Page.valueOf(pageName);
        return path;
    }
}
