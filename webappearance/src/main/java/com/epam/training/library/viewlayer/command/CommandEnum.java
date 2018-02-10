package com.epam.training.library.viewlayer.command;

import com.epam.training.library.daolayer.model.*;
import com.epam.training.library.daolayer.model.dto.assembler.UserDtoAssembler;
import com.epam.training.library.daolayer.service.*;
import com.epam.training.library.daolayer.servicefactory.ServiceManager;

public enum CommandEnum {

    USER_SIGN_OUT() {
        {
            this.command = new SignOutCommand();
        }
    },

    LIBRARY_HOME() {
        {
            BookService bookService = CommandEnum.getBookService();
            this.command = new BookListCommand(bookService);
        }
    },

    USER_SIGN_IN() {
        {
            UserDtoAssembler assembler = new UserDtoAssembler();
            UserService userService = CommandEnum.getUserService();
            this.command = new AuthorizationCommand(userService, assembler);
        }
    },

    USER_REGISTRATION() {
        {
            UserService userService = CommandEnum.getUserService();
            this.command = new UserRegistrationCommand(userService);
        }
    },

    USER_PREPARE_EDIT() {
        {
            UserService userService = CommandEnum.getUserService();
            RoleService roleService = CommandEnum.getRoleService();
            this.command = new UserPrepareUpdateCommand(userService, roleService);
        }
    },

    USER_EDIT() {
        {
            UserService userService = CommandEnum.getUserService();
            RoleService roleService = CommandEnum.getRoleService();
            this.command = new UserUpdateCommand(userService, roleService);
        }
    },

    BOOK_PREPARE_EDIT() {
        {
            BookService bookService = CommandEnum.getBookService();
            this.command = new BookPrepareUpdateCommand(bookService);
        }
    },

    BOOK_EDIT() {
        {
            BookService bookService = CommandEnum.getBookService();
            this.command = new BookUpdateCommand(bookService);
        }
    },

    SHOW_PAGE() {
        {
            this.command = new ChangePageCommand();
        }
    },

    BOOK_ORDER() {
        {
            ReceiptService receiptService = CommandEnum.getReceiptService();
            this.command = new BookOrderCommand(receiptService);
        }
    },

    EMPTY_COMMAND() {
        {
            this.command = new EmptyCommand();
        }
    },

    ADD_BOOK_TO_CART() {
        {
            BookService bookService = CommandEnum.getBookService();
            this.command = new AddBookToCartCommand(bookService);
        }
    },

    RECEIPT_LIST() {
        {
            ReceiptService receiptService = CommandEnum.getReceiptService();
            this.command = new ReceiptListCommand(receiptService);
        }
    },

    BOOK_TRANSFER() {
        {
            ReceiptService receiptService = CommandEnum.getReceiptService();
            this.command = new TransferBookCommand(receiptService);
        }
    },

    RECEIPT_DELETE() {
        {
            ReceiptService receiptService = CommandEnum.getReceiptService();
            this.command = new DeleteReceiptCommand(receiptService);
        }
    },

    USER_LIST() {
        {
            UserService userService = CommandEnum.getUserService();
            this.command = new UserListCommand(userService);
        }
    },

    USER_DELETE() {
        {
            UserService userService = CommandEnum.getUserService();
            this.command = new DeleteUserCommand(userService);
        }
    },

    USER_RECEIPT() {
        {
            ReceiptService serviceInstance = CommandEnum.getReceiptService();
            this.command = new UserReceiptCommand(serviceInstance);
        }
    },

    CHANGE_LOCALE() {
        {
            this.command = new ChangeLocaleCommand();
        }
    };

    CommandEnum() {
    }

    AbstractActionCommand command;

    public AbstractActionCommand getCommand() {
        return command;
    }

    private static BookService bookService;
    private static UserService userService;
    private static RoleService roleService;
    private static ReceiptService receiptService;

    private static BookService getBookService() {
        if (bookService == null) {
            bookService = getServiceInstance(Book.class);
        }
        return bookService;
    }

    private static UserService getUserService() {
        if (userService == null) {
            userService = getServiceInstance(User.class);
        }
        return userService;
    }

    private static RoleService getRoleService() {
        if (roleService == null) {
            roleService = getServiceInstance(Role.class);
        }
        return roleService;
    }

    private static ReceiptService getReceiptService() {
        if (receiptService == null) {
            receiptService = getServiceInstance(Receipt.class);
        }
        return receiptService;
    }

    private static <T extends Identified, K extends Service> K getServiceInstance(Class<T> serviceClass) {
        ServiceManager serviceManager = ServiceManager.getInstance();
        K service = serviceManager.getService(serviceClass);
        return service;
    }
}
