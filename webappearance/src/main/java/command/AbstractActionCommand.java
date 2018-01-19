package command;

import command.exception.ActionException;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.RequestManager;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractActionCommand implements ActionCommand {
    private static final Logger Log = LogManager.getLogger(AbstractActionCommand.class.getSimpleName());

    public abstract String execute(HttpServletRequest request) throws ActionException;

    protected String buildPathMap(Page actionType, Page pageName) {
        String path = actionType.toString().concat(pageName.toString());
        return path;
    }

    protected String getHomePageByRole(HttpServletRequest request) {
        User user = RequestManager.getUserFromSession(request);
        Role role = user.getRole();
        Long roleId = role.getId();
        String path = Page.EMPTY.toString();
        Log.debug("Redirect by role");
        if (roleId.equals(Role.READER_ROLE_ID)) {
            Log.info("Authorized successfully as: " + roleId);
            String pathMap = buildPathMap(Page.REDIRECT, Page.RESULT);
            path = path.concat(pathMap);

        } else if (roleId.equals(Role.ADMIN_ROLE_ID) || roleId.equals(Role.LIBRARIAN_ROLE_ID)) {
            Log.info("Authorized successfully as: " + roleId);
            String pathMap = buildPathMap(Page.REDIRECT, Page.ADMIN_PANEL);
            path = path.concat(pathMap);

        } else {
            String pathMap = buildPathMap(Page.REDIRECT, Page.ERROR);
            path = path.concat(pathMap);
            Log.error("Incorrect role");
        }

        return path;
    }
}
