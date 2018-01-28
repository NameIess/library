package model;

import java.io.Serializable;

public class Receipt implements Identified, Serializable {
    public static final String ID_ALIAS = "id";
    public static final String QUANTITY_ALIAS = "quantity";
    public static final String IS_SUBSCRIPTION_ALIAS = "is_subscribtion";
    public static final String TERM_ALIAS = "term";

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

    public void setSubscription(Boolean subscription) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Receipt)) {
            return false;
        }

        Receipt receipt = (Receipt) o;

        if (quantity != receipt.quantity) {
            return false;
        }

        if (id != null ? !id.equals(receipt.id) : receipt.id != null) {
            return false;
        }

        if (status != null ? !status.equals(receipt.status) : receipt.status != null) {
            return false;
        }

        if (user != null ? !user.equals(receipt.user) : receipt.user != null) {
            return false;
        }

        if (book != null ? !book.equals(receipt.book) : receipt.book != null) {
            return false;
        }

        if (isSubscription != null ? !isSubscription.equals(receipt.isSubscription) : receipt.isSubscription != null) {
            return false;
        }

        return term != null ? term.equals(receipt.term) : receipt.term == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (isSubscription != null ? isSubscription.hashCode() : 0);
        result = 31 * result + (term != null ? term.hashCode() : 0);
        return result;
    }
}
