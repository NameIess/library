package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.UserService;
import com.epam.training.library.daolayer.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteUserCommand extends AbstractActionCommand {
    private UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = parseRequestData(request);
        try {
            userService.delete(user);
            HttpSession session = request.getSession(false);
            session.setAttribute(Message.SUCCESS.toString(), Message.USER_DELETED.toString());
        } catch (ServiceException e) {
            throw new ActionException("Error within DeleteUserCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.REDIRECT, Page.RESULT);
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
