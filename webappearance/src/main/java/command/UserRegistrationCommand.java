package command;

import command.exception.ActionException;
import dao.UserDao;
import daofactory.DaoFactory;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrationCommand extends AbstractActionCommand {
    private static final Logger Log = LogManager.getLogger(UserRegistrationCommand.class.getSimpleName());
    private UserService userService;

    public UserRegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = parseRequestData(request);
        Log.info("User before registration: " + user);
        try {
            userService.registration(user);
        } catch (ServiceException e) {
            throw new ActionException("Error within UserRegistrationCommand execute(): " + e.getMessage(), e);
        }

        String path = buildPathMap(Page.REDIRECT, Page.LIBRARY_HOME);
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
