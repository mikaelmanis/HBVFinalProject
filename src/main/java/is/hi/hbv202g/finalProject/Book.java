package is.hi.hbv202g.finalProject;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a book with a title and a list of authors.
 */
class Book {
    private String title;
    private List<Author> authors;

    /**
     * Constructs a Book with the specified title and a single author.
     *
     * @param title      the title of the book
     * @param authorName the name of the author
     */
    public Book(String title, String authorName) {
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.add(new Author(authorName));
    }

    /**
     * Constructs a Book with the specified title and a list of authors.
     *
     * @param title   the title of the book
     * @param authors the list of authors
     * @throws EmptyAuthorListException if the author list is empty
     */
    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        this.title = title;
        this.authors = authors;
    }

    /**
     * Returns the list of authors of the book.
     *
     * @return the list of authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Sets the list of authors for the book.
     *
     * @param authors the new list of authors
     * @throws EmptyAuthorListException if the author list is empty
     */
    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        this.authors = authors;
    }

    /**
     * Adds an author to the book's list of authors.
     *
     * @param author the author to add
     */
    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    /**
     * Returns the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title of the book
     */
    public void setTitle(String title) {
    }
}