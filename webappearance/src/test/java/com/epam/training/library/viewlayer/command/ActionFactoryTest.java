package com.epam.training.library.viewlayer.command;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class ActionFactoryTest {
    private ActionFactory underTest = ActionFactory.getInstance();

    @DataProvider
    public static Object[][] validCommandNames() {
        return new Object[][]{
                {"LIBRARY_HOME", CommandEnum.LIBRARY_HOME.getCommand()},
                {"USER_EDIT", CommandEnum.USER_EDIT.getCommand()},
                {"SHOW_PAGE", CommandEnum.SHOW_PAGE.getCommand()},
                {"BOOK_TRANSFER", CommandEnum.BOOK_TRANSFER.getCommand()},
                {"CHANGE_LOCALE", CommandEnum.CHANGE_LOCALE.getCommand()},
                {"USER_REGISTRATION", CommandEnum.USER_REGISTRATION.getCommand()},
                {"EMPTY_COMMAND", CommandEnum.EMPTY_COMMAND.getCommand()},
                {"BOOK_ORDER", CommandEnum.BOOK_ORDER.getCommand()}
        };
    }

    @DataProvider
    public static Object[][] invalidCommandNames() {
        return new Object[][]{
                {"LIBRARY_HME", CommandEnum.LIBRARY_HOME.getCommand()},
                {"USER_", CommandEnum.USER_EDIT.getCommand()},
                {"SHO", CommandEnum.SHOW_PAGE.getCommand()},
                {"BOOKER", CommandEnum.BOOK_TRANSFER.getCommand()},
                {"change_locale", CommandEnum.CHANGE_LOCALE.getCommand()},
                {"useR_registration", CommandEnum.USER_REGISTRATION.getCommand()},
                {"EMPTy_COMMAND", CommandEnum.EMPTY_COMMAND.getCommand()},
                {"", CommandEnum.BOOK_ORDER.getCommand()}
        };
    }

    @Test
    @UseDataProvider("validCommandNames")
    public void shouldReturnParticularCommandWhenCommandNameValid(String commandName, AbstractActionCommand expectedInstance) {
        ActionCommand actualCommand = underTest.defineCommand(commandName);
        Assert.assertEquals(actualCommand, expectedInstance);
    }

    @Test(expected = IllegalArgumentException.class)
    @UseDataProvider("invalidCommandNames")
    public void shouldNotReturnParticularCommandWhenCommandNameInalid(String commandName, AbstractActionCommand expectedInstance) {
        underTest.defineCommand(commandName);
    }
}
