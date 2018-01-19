package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(ChangeLocaleCommand.class.getSimpleName());

    @Override
    public String execute(HttpServletRequest request) {
        String localeAbbreviation = request.getParameter(Page.LOCALE_PARAMETER.toString());
        Log.debug("Current locale abbreviation: " + localeAbbreviation);
        request.getSession(true).setAttribute(Page.LOCALE_PARAMETER.toString(), localeAbbreviation);
        String path = buildPathMap(Page.REDIRECT, Page.RESULT);
        return path;
    }
}
