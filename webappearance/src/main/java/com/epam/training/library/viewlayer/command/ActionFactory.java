package com.epam.training.library.viewlayer.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ActionFactory {
    private static final Logger Log = LogManager.getLogger(ActionFactory.class.getSimpleName());
    private static ActionFactory instance;

    private ActionFactory() {
    }

    public static ActionFactory getInstance() {
        if (instance == null) {
            instance = new ActionFactory();
        }
        return instance;
    }

    public ActionCommand defineCommand(String commandName) {
        CommandEnum currentCommand = CommandEnum.valueOf(commandName);
        AbstractActionCommand command = currentCommand.getCommand();

        Log.info("Command " + command.getClass().getSimpleName() + " has been received for " + commandName);
        return command;
    }
}
