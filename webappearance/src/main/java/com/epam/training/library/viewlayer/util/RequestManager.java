package com.epam.training.library.viewlayer.util;

import com.epam.training.library.daolayer.model.dto.impl.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class RequestManager {
    private static final String ACTION_ATTRIBUTE = "command";
    private static final String SESSION_USER_ATTRIBUTE = "user";
    private static final String EMPTY_ACTION_COMMAND_NAME = "empty_command";

    private RequestManager() {
    }

    public static UserDto getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UserDto currentUser = null;
        if (session != null) {
            Object currentUserWrapper = session.getAttribute(SESSION_USER_ATTRIBUTE);
            currentUser = (UserDto) currentUserWrapper;
        }

        return currentUser;
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
