package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignOutCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(SignOutCommand.class.getSimpleName());

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        Log.info("Session has been invalidated");
        String path = buildPathMap(Page.FORWARD, Page.USER_SIGN_IN);
        return path;
    }
}
