package command;

import command.exception.ActionException;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserListCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(UserListCommand.class.getSimpleName());
    private static final String REQUEST_USERS_ATTRIBUTE = "users";
    private UserService userService;

    public UserListCommand(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        try {
            List<User> userList = userService.findAll();
            request.setAttribute(REQUEST_USERS_ATTRIBUTE, userList);
        } catch (ServiceException e) {
            throw new ActionException("Error within UserListCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.FORWARD, Page.USER_LIST);
        return path;
    }
}
