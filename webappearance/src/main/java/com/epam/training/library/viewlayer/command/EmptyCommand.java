package com.epam.training.library.viewlayer.command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand extends AbstractActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = buildPathMap(Page.REDIRECT, Page.USER_SIGN_IN);
        return page;
    }
}
