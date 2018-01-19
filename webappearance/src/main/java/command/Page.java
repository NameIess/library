package command;

public enum  Page {
    REQUEST_REDIRECT_PARAMETER ("redirect"),
    REDIRECT ("redirect:"),
    FORWARD ("forward:"),
    ADMIN_PANEL ("admin_panel"),
    CART ("cart"),
    USER_REGISTRATION ("user_registration"),
    USER_SIGN_IN ("user_sign_in"),
    RESULT ("result"),
    INDEX ("index"),
    LIBRARY_HOME ("library_home"),
    RECEIPT_LIST ("receipt_list"),
    JSP_EXTENSION (".jsp"),
    VIEWS_PATH ("/WEB-INF/jsp/"),
    CHANGE_PAGE ("/libraryDispatcher?command=show_page&redirect="),
    ERROR ("error"),
    USER_LIST ("user_list"),
    USER_RECEIPT ("user_receipt"),
    EMPTY (""),
    LOCALE_PARAMETER ("locale");


    private String page;

    Page(String page) {
        this.page = page;
    }

    public String toString() {
        return this.page;
    }


}
