package model;

public class Book implements Identified {
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
}
