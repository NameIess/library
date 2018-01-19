package command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ActionFactory {
    private static final Logger Log = LogManager.getLogger(ActionFactory.class.getSimpleName());

    public ActionCommand defineCommand(String commandName) {
        CommandEnum currentCommand = CommandEnum.valueOf(commandName);
        AbstractActionCommand command = currentCommand.getCommand();

        Log.info("Command " + command.getClass().getSimpleName() + " has been received for " + commandName);
        return command;
    }
}
