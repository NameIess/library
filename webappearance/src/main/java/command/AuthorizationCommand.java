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
    private static final String ERROR_AUTHORIZATION_MESSAGE_ATTRIBUTE = "errorAuthorizationMessage";
    private UserService userService;

    public AuthorizationCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = parseRequestData(request);
        try {
            user = userService.findRegisteredUser(user);
        } catch (ServiceException e) {
            throw new ActionException("Error within AuthorizationCommand execute(): " + e.getMessage(), e);
        }

        String path;
        if (user != null) {
            Log.debug("Open session for user: " + user);
            HttpSession session = request.getSession(true);
            session.setAttribute(User.TABLE_NAME, user);
            session.setAttribute(Message.SUCCESS.toString(), Message.AUTHENTICATED_SUCCESSFULLY.toString());
            path = buildPathMap(Page.REDIRECT, Page.RESULT);
        } else {
            request.setAttribute(ERROR_AUTHORIZATION_MESSAGE_ATTRIBUTE, Message.INVALID_LOGIN_OR_PASSWORD.toString());
            path = buildPathMap(Page.FORWARD, Page.USER_SIGN_IN);
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
