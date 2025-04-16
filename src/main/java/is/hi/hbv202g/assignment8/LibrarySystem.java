package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class LibrarySystem {
    private List<Book> books;
    private List<User> users;
    private List<Lending> lendings;

    public LibrarySystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        lendings = new ArrayList<>();
    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        books.add(new Book(title, authors));
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) throws EmptyAuthorListException {
        books.add(new Book(title, authorName));
    }

    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
    }

    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
    }

    public Book findBookByTitle(String title) throws UserOrBookDoesNotExistException {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new UserOrBookDoesNotExistException("Book " + title + " not found");
    }

    public User findUserByName(String name) throws UserOrBookDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserOrBookDoesNotExistException("User " + name + " not found");
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (findBookByTitle(book.getTitle()) == null || findUserByName(user.getName()) == null) {
            throw new UserOrBookDoesNotExistException("Book " + book.getTitle() + " or user " + user.getName() + " not found");
        }
        lendings.add(new Lending(book, user));
    }

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
