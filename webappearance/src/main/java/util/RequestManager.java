package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.User;

public final class RequestManager {
    private static final String ACTION_ATTRIBUTE = "command";
    private static final String SESSION_USER_ATTRIBUTE = "user";
    private static final String EMPTY_ACTION_COMMAND_NAME = "empty_command";

    private RequestManager() {
    }

    public static User getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session == null ? null : (User)session.getAttribute(SESSION_USER_ATTRIBUTE);
    }

    public static String getActionName(HttpServletRequest request) {
        String action = request.getParameter(ACTION_ATTRIBUTE);
        if (action != null && !action.isEmpty()) {
            action = action.toUpperCase();
            return action;
        } else {
            return EMPTY_ACTION_COMMAND_NAME;
        }
    }
}
