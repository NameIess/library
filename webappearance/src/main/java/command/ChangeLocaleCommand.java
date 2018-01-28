package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(ChangeLocaleCommand.class.getSimpleName());

    @Override
    public String execute(HttpServletRequest request) {
        String localeAbbreviation = request.getParameter(Page.LOCALE_PARAMETER.toString());
        HttpSession session = request.getSession(true);
        session.setAttribute(Page.LOCALE_PARAMETER.toString(), localeAbbreviation);
        session.setAttribute(Message.SUCCESS.toString(), Message.LOCALE_CHANGED.toString());
        String path = buildPathMap(Page.REDIRECT, Page.RESULT);

        Log.debug("New locale abbreviation: " + localeAbbreviation);
        return path;
    }
}
