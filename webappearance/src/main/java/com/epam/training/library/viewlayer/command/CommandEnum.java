package com.epam.training.library.viewlayer.command;

import com.epam.training.library.daolayer.dao.BookDao;
import com.epam.training.library.daolayer.dao.ReceiptDao;
import com.epam.training.library.daolayer.dao.RoleDao;
import com.epam.training.library.daolayer.dao.UserDao;
import com.epam.training.library.daolayer.daofactory.DaoFactory;
import com.epam.training.library.daolayer.model.Book;
import com.epam.training.library.daolayer.model.Receipt;
import com.epam.training.library.daolayer.model.Role;
import com.epam.training.library.daolayer.model.User;
import com.epam.training.library.daolayer.service.impl.BookServiceImpl;
import com.epam.training.library.daolayer.service.impl.ReceiptServiceImpl;
import com.epam.training.library.daolayer.service.impl.RoleServiceImpl;
import com.epam.training.library.daolayer.service.impl.UserServiceImpl;
import com.epam.training.library.daolayer.util.PasswordEncoder;
import com.epam.training.library.daolayer.validator.UserSignInValidator;

public enum CommandEnum {

    USER_SIGN_OUT(new SignOutCommand()) {
    },

    LIBRARY_HOME(new BookListCommand(new BookServiceImpl((BookDao) new DaoFactory().getDao(Book.class)))) {
    },

    USER_SIGN_IN(new AuthorizationCommand(new UserServiceImpl((UserDao) new DaoFactory().getDao(User.class), new PasswordEncoder(), new UserSignInValidator()))) {
    },

    USER_REGISTRATION(new UserRegistrationCommand(new UserServiceImpl((UserDao) new DaoFactory().getDao(User.class), new PasswordEncoder(), new UserSignInValidator()))) {
    },

    USER_PREPARE_EDIT(new UserPrepareUpdateCommand(new UserServiceImpl((UserDao) new DaoFactory().getDao(User.class), new PasswordEncoder(), new UserSignInValidator()), new RoleServiceImpl((RoleDao) new DaoFactory().getDao(Role.class)))) {
    },

    USER_EDIT(new UserUpdateCommand(new UserServiceImpl((UserDao) new DaoFactory().getDao(User.class), new PasswordEncoder(), new UserSignInValidator()))) {
    },

    SHOW_PAGE(new ChangePageCommand()) {
    },

    BOOK_ORDER(new BookOrderCommand(new ReceiptServiceImpl((ReceiptDao) new DaoFactory().getDao(Receipt.class), (BookDao) new DaoFactory().getDao(Book.class)))) {
    },

    EMPTY_COMMAND(new EmptyCommand()) {
    },

    ADD_BOOK_TO_CART(new AddBookToCartCommand(new BookServiceImpl((BookDao) new DaoFactory().getDao(Book.class)))) {
    },

    RECEIPT_LIST(new ReceiptListCommand(new ReceiptServiceImpl((ReceiptDao) new DaoFactory().getDao(Receipt.class), (BookDao) new DaoFactory().getDao(Book.class)))) {
    },

    BOOK_TRANSFER(new TransferBookCommand(new ReceiptServiceImpl((ReceiptDao) new DaoFactory().getDao(Receipt.class), (BookDao) new DaoFactory().getDao(Book.class)))) {
    },

    RECEIPT_DELETE(new DeleteReceiptCommand(new ReceiptServiceImpl((ReceiptDao) new DaoFactory().getDao(Receipt.class), (BookDao) new DaoFactory().getDao(Book.class)))) {
    },

    USER_LIST(new UserListCommand(new UserServiceImpl((UserDao) new DaoFactory().getDao(User.class), new PasswordEncoder(), new UserSignInValidator()))) {
    },

    USER_DELETE(new DeleteUserCommand(new UserServiceImpl((UserDao) new DaoFactory().getDao(User.class), new PasswordEncoder(), new UserSignInValidator()))) {
    },

    USER_RECEIPT(new UserReceiptCommand(new ReceiptServiceImpl((ReceiptDao) new DaoFactory().getDao(Receipt.class), (BookDao) new DaoFactory().getDao(Book.class)))) {
    },

    CHANGE_LOCALE(new ChangeLocaleCommand()) {
    };


    private AbstractActionCommand command;

    CommandEnum(AbstractActionCommand command) {
        this.command = command;
    }

    public AbstractActionCommand getCommand() {
        return command;
    }

}
