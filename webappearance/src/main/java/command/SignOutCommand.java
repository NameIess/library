package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(SignOutCommand.class.getSimpleName());

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        Log.info("Session has been invalidated");

        request.setAttribute(Message.SUCCESS.toString(), Message.USER_LOGOUT_SUCCESSFULLY.toString());
        String path = buildPathMap(Page.FORWARD, Page.USER_SIGN_IN);
        return path;
    }
}
