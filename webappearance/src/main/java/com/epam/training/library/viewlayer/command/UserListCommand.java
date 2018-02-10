package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.UserService;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserListCommand extends AbstractActionCommand {
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
