package model;

public class Receipt implements Identified {
    public static final String ID_ALIAS = "id";
    public static final String STATUS_ID_ALIAS = "status_id";
    public static final String USER_ID_ALIAS = "user_id";
    public static final String BOOK_ID_ALIAS = "book_id";
    public static final String QUANTITY_ALIAS = "quantity";
    public static final String IS_SUBSCRIPTION_ALIAS = "is_subscribtion";
    public static final String TERM_ALIAS = "term";
    public static final String TABLE_NAME = "receipt";

    private Long id;
    private Status status;
    private User user;
    private Book book;
    private int quantity;
    private Boolean isSubscription;
    private String term;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean isSubscription() {
        return isSubscription;
    }

    public void setSubscription(boolean subscription) {
        isSubscription = subscription;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Receipt{");
        sb.append("id=").append(id);
        sb.append(", status=").append(status);
        sb.append(", user=").append(user);
        sb.append(", book=").append(book);
        sb.append(", quantity=").append(quantity);
        sb.append(", isSubscription=").append(isSubscription);
        sb.append(", term='").append(term).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
