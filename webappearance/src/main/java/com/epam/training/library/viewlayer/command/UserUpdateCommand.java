package com.epam.training.library.viewlayer.command;

import com.epam.training.library.daolayer.model.Role;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.RoleService;
import com.epam.training.library.daolayer.service.UserService;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import com.epam.training.library.viewlayer.command.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserUpdateCommand extends AbstractActionCommand {
    private UserService userService;
    private RoleService roleService;

    public UserUpdateCommand(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = parseRequestData(request);

        try {
            String roleIdParameter = request.getParameter(Role.FOREIGN_KEY_ID_ALIAS);
            Long roleId = Long.valueOf(roleIdParameter);
            Role role = roleService.findOneById(roleId);
            user.setRole(role);
            userService.update(user);
            HttpSession session = request.getSession(false);
            session.setAttribute(Message.SUCCESS.toString(), Message.ACCOUNT_UPDATED_SUCCESSFULLY.toString());
        } catch (ServiceException e) {
            throw new ActionException("Error within UserUpdateCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.REDIRECT, Page.RESULT);
        return path;
    }

    private User parseRequestData(HttpServletRequest request) {
        User user = new User();
        String userIdParameter = request.getParameter(User.FOREIGN_KEY_ID_ALIAS);
        Long userId = Long.valueOf(userIdParameter);
        user.setId(userId);
        String employeeNumber = request.getParameter(User.EMPLOYEE_NUMBER_ALIAS);
        user.setEmployeeNumber(employeeNumber);
        String name = request.getParameter(User.NAME_ALIAS);
        user.setName(name);
        String secondName = request.getParameter(User.SECOND_NAME_ALIAS);
        user.setSecondName(secondName);
        String surname = request.getParameter(User.SURNAME_ALIAS);
        user.setSurname(surname);
        String email = request.getParameter(User.EMAIL_ALIAS);
        user.setEmail(email);
        String password = request.getParameter(User.PASSWORD_ALIAS);
        user.setPassword(password);
        String phoneNumber = request.getParameter(User.PHONE_NUMBER_ALIAS);
        user.setPhoneNumber(phoneNumber);
        String passportSeries = request.getParameter(User.PASSPORT_SERIES_ALIAS);
        user.setPassportSeries(passportSeries);
        String passportNumber = request.getParameter(User.PASSPORT_NUMBER_ALIAS);
        user.setPassportNumber(passportNumber);

        return user;
    }
}
