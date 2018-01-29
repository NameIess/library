package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractActionCommand implements ActionCommand {

    public abstract String execute(HttpServletRequest request) throws ActionException;

    protected String buildPathMap(Page actionType, Page pageName) {
        String path = actionType.toString().concat(pageName.toString());
        return path;
    }
}
