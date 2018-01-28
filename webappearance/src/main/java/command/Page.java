package command;

public enum  Page {
    REQUEST_REDIRECT_PARAMETER ("redirect"),
    REDIRECT ("redirect:"),
    FORWARD ("forward:"),
    CART ("cart"),
    USER_REGISTRATION ("user_registration"),
    USER_SIGN_IN ("user_sign_in"),
    RESULT ("result"),
    LIBRARY_HOME ("library_home"),
    RECEIPT_LIST ("receipt_list"),
    JSP_EXTENSION (".jsp"),
    VIEWS_PATH ("/WEB-INF/jsp/"),
    CHANGE_PAGE ("/libraryDispatcher?command=show_page&redirect="),
    USER_LIST ("user_list"),
    USER_RECEIPT ("user_receipt"),
    LOCALE_PARAMETER ("locale");

    private String page;

    Page(String page) {
        this.page = page;
    }

    public String toString() {
        return this.page;
    }


}
