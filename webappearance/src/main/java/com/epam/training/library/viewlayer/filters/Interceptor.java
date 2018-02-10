package com.epam.training.library.viewlayer.filters;

import com.epam.training.library.daolayer.model.Role;
import com.epam.training.library.viewlayer.command.CommandEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Interceptor {
    private static final Logger Log = LogManager.getLogger(Interceptor.class.getSimpleName());
    private static final Interceptor INSTANCE = new Interceptor();
    private Set<String> adminAllowedActions = new HashSet<>();
    private Set<String> librarianAllowedActions = new HashSet<>();
    private Set<String> readerAllowedAction = new HashSet<>();
    private Set<String> anonymousAllowedActions = new HashSet<>();

    private Interceptor() {
        anonymousAllowedActions.add(CommandEnum.USER_REGISTRATION.toString());
        anonymousAllowedActions.add(CommandEnum.USER_SIGN_IN.toString());
        anonymousAllowedActions.add(CommandEnum.SHOW_PAGE.toString());
        anonymousAllowedActions.add(CommandEnum.EMPTY_COMMAND.toString());
        anonymousAllowedActions.add(CommandEnum.LIBRARY_HOME.toString());
        anonymousAllowedActions.add(CommandEnum.CHANGE_LOCALE.toString());

        readerAllowedAction.addAll(anonymousAllowedActions);
        readerAllowedAction.add(CommandEnum.USER_SIGN_OUT.toString());
        readerAllowedAction.add(CommandEnum.ADD_BOOK_TO_CART.toString());
        readerAllowedAction.add(CommandEnum.BOOK_ORDER.toString());
        readerAllowedAction.add(CommandEnum.RECEIPT_DELETE.toString());
        readerAllowedAction.add(CommandEnum.USER_RECEIPT.toString());

        librarianAllowedActions.addAll(readerAllowedAction);
        librarianAllowedActions.add(CommandEnum.RECEIPT_LIST.toString());
        librarianAllowedActions.add(CommandEnum.BOOK_TRANSFER.toString());

        adminAllowedActions.addAll(librarianAllowedActions);
        adminAllowedActions.add(CommandEnum.BOOK_PREPARE_EDIT.toString());
        adminAllowedActions.add(CommandEnum.BOOK_EDIT.toString());
        adminAllowedActions.add(CommandEnum.USER_LIST.toString());
        adminAllowedActions.add(CommandEnum.USER_DELETE.toString());
        adminAllowedActions.add(CommandEnum.USER_PREPARE_EDIT.toString());
        adminAllowedActions.add(CommandEnum.USER_EDIT.toString());
    }

    public static Interceptor getInstance() {
        return INSTANCE;
    }

    public boolean isAccessAllowed(String accessor, Long roleId) {
        boolean isAllowed;
        if (roleId.equals(Role.ADMIN_ROLE_ID)) {
            isAllowed = adminAllowedActions.contains(accessor);
            Log.debug("Allowed for admin: = " + accessor + isAllowed);

        } else if (roleId.equals(Role.LIBRARIAN_ROLE_ID)) {
            isAllowed = librarianAllowedActions.contains(accessor);
            Log.debug("Allowed for librarian: = " + accessor + isAllowed);

        } else if (roleId.equals(Role.READER_ROLE_ID)) {
            isAllowed = readerAllowedAction.contains(accessor);
            Log.debug("Allowed for reader: = " + accessor + isAllowed);

        } else {
            isAllowed = anonymousAllowedActions.contains(accessor);
            Log.debug("Allowed for anonymous: = " + accessor + isAllowed);
        }

        return isAllowed;
    }
}
