package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library system that manages books, users, and lendings.
 */
class LibrarySystem {
    private List<Book> books;
    private List<User> users;
    private List<Lending> lendings;

    /**
     * Constructs a LibrarySystem with empty lists of books, users, and lendings.
     */
    public LibrarySystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        lendings = new ArrayList<>();
    }

    /**
     * Adds a book with a title and a list of authors to the library.
     *
     * @param title   the title of the book
     * @param authors the list of authors
     * @throws EmptyAuthorListException if the author list is empty
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        books.add(new Book(title, authors));
    }

    /**
     * Adds a book with a title and a single author's name to the library.
     *
     * @param title      the title of the book
     * @param authorName the name of the author
     * @throws EmptyAuthorListException if the author name is invalid
     */
    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) throws EmptyAuthorListException {
        books.add(new Book(title, authorName));
    }

    /**
     * Adds a student user to the library system.
     *
     * @param name    the name of the student
     * @param feePaid whether the student has paid the fee
     */
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    /**
     * Adds a faculty member user to the library system.
     *
     * @param name       the name of the faculty member
     * @param department the department of the faculty member
     */
    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }

    /**
     * Finds a book by its title.
     *
     * @param title the title of the book
     * @return the book with the specified title
     * @throws UserOrBookDoesNotExistException if the book is not found
     */
    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("Book " + title + " not found");
    }

    /**
     * Finds a user by their name.
     *
     * @param name the name of the user
     * @return the user with the specified name
     * @throws UserOrBookDoesNotExistException if the user is not found
     */
    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("User " + name + " not found");
    }

    /**
     * Borrows a book for a user.
     *
     * @param user the user borrowing the book
     * @param book the book to be borrowed
     * @throws UserOrBookDoesNotExistException if the book or user is not found
     */
    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (findBookByTitle(book.getTitle()) == null || findUserByName(user.getName()) == null) {
            throw new UserOrBookDoesNotExistException("Book " + book.getTitle() + " or user " + user.getName() + " not found");
        }
        lendings.add(new Lending(book, user));
    }

    /**
     * Extends the lending period for a faculty member.
     *
     * @param facultyMember the faculty member
     * @param book          the book being lent
     * @param newDueDate    the new due date
     * @throws UserOrBookDoesNotExistException if the book or user is not found
     */
    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate) throws UserOrBookDoesNotExistException {
        if (findBookByTitle(book.getTitle()) == null || findUserByName(facultyMember.getName()) == null) {
            throw new UserOrBookDoesNotExistException("Book " + book.getTitle() + " or user " + facultyMember.getName() + " not found");
        }
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book) && lending.getUser().equals(facultyMember)) {
                lending.setDueDate(newDueDate);
            }
        }
    }

    /**
     * Returns a book borrowed by a user.
     *
     * @param user the user returning the book
     * @param book the book to be returned
     * @throws UserOrBookDoesNotExistException if the book or user is not found
     */
    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (findBookByTitle(book.getTitle()) == null || findUserByName(user.getName()) == null) {
            throw new UserOrBookDoesNotExistException("Book " + book.getTitle() + " or user " + user.getName() + " not found");
        }

        for (Lending lending : lendings) {
            if (lending.getBook().equals(book) && lending.getUser().equals(user)) {
                lendings.remove(lending);
                return;
            }
        }
        throw new UserOrBookDoesNotExistException("Book " + book.getTitle() + " or user " + user.getName() + " not found");
    }
}