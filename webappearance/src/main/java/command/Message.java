package command;

public enum Message {
    SUCCESS("successMessage"),
    INVALID_LOGIN_OR_PASSWORD("local.error_authorization"),
    DUPLICATED_EMAIL("local.this_email_already_in_use"),
    INVALID_ORDERED_ITEMS_AMOUNT("local.invalid_ordered_items_amount"),
    BOOK_REQUEST_SENT("local.successful_request_sent"),
    LOCALE_CHANGED("local.locale_change"),
    RECEIPT_DELETED("local.receipt_has_been_deleted"),
    USER_DELETED("local.user_has_been_deleted"),
    USER_LOGOUT_SUCCESSFULLY("local.user_logout_successfully"),
    FATAL_ERROR("local.fatal_error"),
    BOOK_TRANSFERRED_SUCCESSFULLY("local.transferred_successfully"),
    ACCOUNT_CREATED_SUCCESSFULLY("local.registered_successfully"),
    ACCOUNT_UPDATED_SUCCESSFULLY("local.user_updated_successfully"),
    AUTHENTICATED_SUCCESSFULLY("local.sign_in_successfully");

    private String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
