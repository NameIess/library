package command;

import command.exception.ActionException;
import dao.AbstractDao;
import daofactory.Connectable;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws ActionException;
}
