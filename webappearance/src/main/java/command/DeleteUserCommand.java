package command;

import command.exception.ActionException;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(DeleteUserCommand.class.getSimpleName());
    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = parseRequestData(request);
        try {
            userService.delete(user);
        } catch (ServiceException e) {
            throw new ActionException("Error within DeleteUserCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.REDIRECT, Page.ADMIN_PANEL);
        return path;
    }

    private User parseRequestData(HttpServletRequest request) {
        String idParameter = request.getParameter(User.ID_ALIAS);
        Long id = Long.valueOf(idParameter);
        User user = new User();
        user.setId(id);
        return user;
    }
}
