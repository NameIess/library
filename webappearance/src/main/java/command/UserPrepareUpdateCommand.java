package command;

import command.exception.ActionException;
import model.Role;
import model.User;
import service.RoleService;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserPrepareUpdateCommand extends AbstractActionCommand {
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
            request.setAttribute(REQUEST_EDIT_USER_ATTRIBUTE, user);

            List<Role> roleList = roleService.findAll();
            request.setAttribute(REQUEST_ROLES_ATTRIBUTE, roleList);
        } catch (ServiceException e) {
            throw new ActionException("Error within UserPrepareUpdateCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.FORWARD, Page.USER_REGISTRATION);
        return path;
    }
}
