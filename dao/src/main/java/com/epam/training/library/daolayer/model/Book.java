package com.epam.training.library.daolayer.model;

import java.io.Serializable;

public class Book implements Identified, Serializable {
    public static final String ID_ALIAS = "id";
    public static final String TITLE_ALIAS = "title";
    public static final String AUTHOR_ALIAS = "author";
    public static final String YEAR_OF_PUBLISHING_ALIAS = "year_of_publishing";
    public static final String NUMBER_OF_PAGES_ALIAS = "number_of_pages";
    public static final String DESCRIPTION_ALIAS = "description";
    public static final String AMOUNT_ALIAS = "amount";
    public static final String TABLE_NAME = "book";
    public static final String FOREIGN_KEY_ALIAS = "book_id";
    private Long id;
    private String title;
    private String author;
    private Short yearOfPublishing;
    private Short numberOfPages;
    private String description;
    private Integer amount;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Short getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Short yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public Short getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Short numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", yearOfPublishing=").append(yearOfPublishing);
        sb.append(", numberOfPages=").append(numberOfPages);
        sb.append(", description='").append(description).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Book)) {
            return false;
        }

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) {
            return false;
        }

        if (title != null ? !title.equals(book.title) : book.title != null) {
            return false;
        }

        if (author != null ? !author.equals(book.author) : book.author != null) {
            return false;
        }

        if (yearOfPublishing != null ? !yearOfPublishing.equals(book.yearOfPublishing) : book.yearOfPublishing != null) {
            return false;
        }

        if (numberOfPages != null ? !numberOfPages.equals(book.numberOfPages) : book.numberOfPages != null) {
            return false;
        }

        if (description != null ? !description.equals(book.description) : book.description != null) {
            return false;
        }

        return amount != null ? amount.equals(book.amount) : book.amount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (yearOfPublishing != null ? yearOfPublishing.hashCode() : 0);
        result = 31 * result + (numberOfPages != null ? numberOfPages.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}
