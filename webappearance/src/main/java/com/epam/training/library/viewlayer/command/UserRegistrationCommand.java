package com.epam.training.library.viewlayer.command;

import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.UserService;
import com.epam.training.library.daolayer.service.exception.BusinessException;
import com.epam.training.library.daolayer.service.exception.ServiceException;
import com.epam.training.library.viewlayer.command.exception.ActionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserRegistrationCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(UserRegistrationCommand.class.getSimpleName());
    private static final String ERROR_DUPLICATED_EMAIL_MESSAGE = "duplicated_email";
    private UserService userService;

    public UserRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = parseRequestData(request);
        String path = buildPathMap(Page.REDIRECT, Page.RESULT);
        try {
            userService.registration(user);
            HttpSession session = request.getSession(true);
            session.setAttribute(Message.SUCCESS.toString(), Message.ACCOUNT_CREATED_SUCCESSFULLY.toString());
        } catch (ServiceException e) {
            throw new ActionException("Error within UserRegistrationCommand execute(): " + e.getMessage(), e);
        } catch (BusinessException e) {
            Log.error("Registration error: " + e.getMessage());
            request.setAttribute(ERROR_DUPLICATED_EMAIL_MESSAGE, Message.DUPLICATED_EMAIL.toString());
            path = buildPathMap(Page.FORWARD, Page.USER_REGISTRATION);
        }

        return path;
    }

    private User parseRequestData(HttpServletRequest request) {
        User user = new User();
        String name = request.getParameter(User.NAME_ALIAS);
        user.setName(name);
        String secondName = request.getParameter(User.SECOND_NAME_ALIAS);
        user.setSecondName(secondName);
        String surname = request.getParameter(User.SURNAME_ALIAS);
        user.setSurname(surname);
        String email = request.getParameter(User.EMAIL_ALIAS);
        user.setEmail(email);
        String phoneNumber = request.getParameter(User.PHONE_NUMBER_ALIAS);
        user.setPhoneNumber(phoneNumber);
        String passportSeries = request.getParameter(User.PASSPORT_SERIES_ALIAS);
        user.setPassportSeries(passportSeries);
        String passportNumber = request.getParameter(User.PASSPORT_NUMBER_ALIAS);
        user.setPassportNumber(passportNumber);
        String password = request.getParameter(User.PASSWORD_ALIAS);
        user.setPassword(password);

        return user;
    }

}
