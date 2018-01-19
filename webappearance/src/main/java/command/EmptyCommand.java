package command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand extends AbstractActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = Page.REDIRECT.toString() + Page.USER_SIGN_IN;
        return page;
    }
}
