package command;

import dao.BookDao;
import dao.ReceiptDao;
import dao.RoleDao;
import dao.UserDao;
import daofactory.DaoFactory;
import model.Book;
import model.Receipt;
import model.Role;
import model.User;
import service.impl.BookServiceImpl;
import service.impl.ReceiptServiceImpl;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;
import util.PasswordEncoder;
import validator.UserSignInValidator;

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
