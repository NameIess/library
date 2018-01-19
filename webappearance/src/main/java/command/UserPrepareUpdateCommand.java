package command;

import command.exception.ActionException;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.RoleService;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserPrepareUpdateCommand extends AbstractActionCommand {
    private Logger Log = LogManager.getLogger(UserPrepareUpdateCommand.class.getSimpleName());
    private static final String REQUEST_EDIT_USER_ATTRIBUTE = "edit_user";
    private static final String REQUEST_ROLES_ATTRIBUTE = "roles";
    private UserService userService;
    private RoleService roleService;

    public UserPrepareUpdateCommand(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        String userIdParameter = request.getParameter(User.FOREIGN_KEY_ID_ALIAS);
        Long userId = Long.valueOf(userIdParameter);
        try {
            User user = userService.findOneById(userId);
            Log.debug("Founded user: " + user);
            request.setAttribute(REQUEST_EDIT_USER_ATTRIBUTE, user);

            List<Role> roleList = roleService.findAll();
            Log.debug("Founded roles: " + roleList);
            request.setAttribute(REQUEST_ROLES_ATTRIBUTE, roleList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        String path = buildPathMap(Page.FORWARD, Page.USER_REGISTRATION);
        return path;
    }
}
