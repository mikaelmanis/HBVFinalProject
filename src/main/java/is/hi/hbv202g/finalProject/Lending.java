package is.hi.hbv202g.finalProject;

import java.time.LocalDate;

/**
 * Represents a lending of a book to a user with a due date.
 */
class Lending {
    private LocalDate dueDate;
    private Book book;
    private User user;

    /**
     * Constructs a Lending with the specified book and user.
     * The due date is set to 30 days from the current date.
     *
     * @param book the book being lent
     * @param user the user borrowing the book
     */
    public Lending(Book book, User user) {
        this.book = book;
        this.user = user;
        this.dueDate = LocalDate.now().plusDays(30);
    }

    /**
     * Returns the due date of the lending.
     *
     * @return the due date
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the lending.
     *
     * @param dueDate the new due date
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the book being lent.
     *
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book being lent.
     *
     * @param book the new book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Returns the user borrowing the book.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user borrowing the book.
     *
     * @param user the new user
     */
    public void setUser(User user) {
        this.user = user;
    }
}