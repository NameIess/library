package command;

import command.exception.ActionException;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthorizationCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(AuthorizationCommand.class.getSimpleName());
    private UserService userService;

    public AuthorizationCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = parseRequestData(request);
        String path = Page.EMPTY.toString();
        try {
            user = userService.findRegisteredUser(user);
            if (user != null) {
                Log.debug("Open session for user: " + user);
                HttpSession session = request.getSession(true);
                session.setAttribute(User.TABLE_NAME, user);
                path = path.concat(getHomePageByRole(request));
            } else {
                path = path.concat(Page.REDIRECT.toString() + Page.ERROR.toString());
            }
        } catch (ServiceException e) {
            throw new ActionException("Error within AuthorizationCommand execute(): " + e.getMessage(), e);
        }

        return path;
    }

    private User parseRequestData(HttpServletRequest request) {
        User user = new User();
        String email = request.getParameter(User.EMAIL_ALIAS);
        user.setEmail(email);
        String password = request.getParameter(User.PASSWORD_ALIAS);
        user.setPassword(password);
        return user;
    }
}
