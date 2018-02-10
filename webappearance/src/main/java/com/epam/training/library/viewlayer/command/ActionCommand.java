package com.epam.training.library.viewlayer.command;

import com.epam.training.library.viewlayer.command.exception.ActionException;

import javax.servlet.http.HttpServletRequest;

/**
 *  * Contains the basic operation for interacting with the request object
 */
public interface ActionCommand {

    /**
     * After completing the request analysis, returns the page name
     * @param request — instance that contains an information about the further actions
     * @return page name
     * @throws ActionException when command processing occurs
     */
    String execute(HttpServletRequest request) throws ActionException;
}
