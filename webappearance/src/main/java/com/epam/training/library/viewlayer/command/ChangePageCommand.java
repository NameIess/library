package com.epam.training.library.viewlayer.command;

import javax.servlet.http.HttpServletRequest;

public class ChangePageCommand extends AbstractActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String pageNameParameter = request.getParameter(Page.REQUEST_REDIRECT_PARAMETER.toString());
        String pageName = pageNameParameter.toUpperCase();
        String path = buildPathMap(Page.FORWARD, Page.valueOf(pageName));
        return path;
    }
}
