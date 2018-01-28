package command;

import command.exception.ActionException;
import model.Role;
import model.User;
import service.UserService;
import service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserUpdateCommand extends AbstractActionCommand {
    private UserService userService;

    public UserUpdateCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws ActionException {
        User user = parseRequestData(request);

        try {
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
        String phoneNumber = request.getParameter(User.PHONE_NUMBER_ALIAS);
        user.setPhoneNumber(phoneNumber);
        String passportSeries = request.getParameter(User.PASSPORT_SERIES_ALIAS);
        user.setPassportSeries(passportSeries);
        String passportNumber = request.getParameter(User.PASSPORT_NUMBER_ALIAS);
        user.setPassportNumber(passportNumber);
        String roleIdParameter = request.getParameter(Role.FOREIGN_KEY_ID_ALIAS);
        Long roleId = Long.valueOf(roleIdParameter);
        Role role = new Role();
        role.setId(roleId);
        user.setRole(role);

        return user;
    }
}
