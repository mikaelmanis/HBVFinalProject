package is.hi.hbv202g.assignment8;

import java.io.IOException;
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

    private static final String STUDENTS_FILE = "students.csv";
    private static final String FACULTY_FILE = "faculty.csv";
    private static final String BOOKS_FILE = "books.csv";
    private static final String LENDINGS_FILE = "lendings.csv";

    /**
     * Saves the list of student users to a CSV file.
     *
     * @throws IOException if an I/O error occurs
     */
    public void saveStudents() throws IOException {
        List<String[]> data = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student) {
                data.add(new String[]{user.getName(), String.valueOf(((Student) user).isFeePaid())});
            }
        }
        CSVUtils.writeCSV(STUDENTS_FILE, data);
    }

    /**
     * Loads the list of student users from a CSV file.
     *
     * @throws IOException if an I/O error occurs
     */
    public void loadStudents() throws IOException {
        List<String[]> data = CSVUtils.readCSV(STUDENTS_FILE);
        users.clear();
        for (String[] row : data) {
            String name = row[0];
            boolean feePaid = Boolean.parseBoolean(row[1]);
            users.add(new Student(name, feePaid));
        }
    }

    /**
     * Saves the list of faculty users to a CSV file.
     *
     * @throws IOException if an I/O error occurs
     */
    public void saveFaculty() throws IOException {
        List<String[]> data = new ArrayList<>();
        for (User user : users) {
            if (user instanceof FacultyMember) {
                data.add(new String[]{user.getName(), ((FacultyMember) user).getDepartment()});
            }
        }
        CSVUtils.writeCSV(FACULTY_FILE, data);
    }

    /**
     * Loads the list of faculty users from a CSV file.
     *
     * @throws IOException if an I/O error occurs
     */
    public void loadFaculty() throws IOException {
        List<String[]> data = CSVUtils.readCSV(FACULTY_FILE);
        users.clear();
        for (String[] row : data) {
            String name = row[0];
            String department = row[1];
            users.add(new FacultyMember(name, department));
        }
    }

    /**
     * Saves the list of books to a CSV file.
     *
     * @throws IOException if an I/O error occurs
     */
    public void saveBooks() throws IOException {
        List<String[]> data = new ArrayList<>();
        for (Book book : books) {
            String authors = String.join(";", book.getAuthors().stream().map(Author::getName).toList());
            data.add(new String[]{book.getTitle(), authors});
        }
        CSVUtils.writeCSV(BOOKS_FILE, data);
    }

    /**
     * Loads the list of books from a CSV file.
     *
     * @throws IOException if an I/O error occurs
     */
    public void loadBooks() throws IOException {
        List<String[]> data = CSVUtils.readCSV(BOOKS_FILE);
        books.clear();
        for (String[] row : data) {
            String title = row[0];
            String[] authorNames = row[1].split(";");
            List<Author> authors = new ArrayList<>();
            for (String name : authorNames) {
                authors.add(new Author(name));
            }
            try {
                books.add(new Book(title, authors));
            } catch (EmptyAuthorListException e) {
                System.out.println("Error loading book: " + e.getMessage());
            }
        }
    }

    /**
     * Saves the list of lendings to a CSV file.
     *
     * @throws IOException if an I/O error occurs
     */
    public void saveLendings() throws IOException {
        List<String[]> data = new ArrayList<>();
        for (Lending lending : lendings) {
            data.add(new String[]{lending.getBook().getTitle(), lending.getUser().getName(), lending.getDueDate().toString()});
        }
        CSVUtils.writeCSV(LENDINGS_FILE, data);
    }

    /**
     * Loads the list of lendings from a CSV file.
     *
     * @throws IOException if an I/O error occurs
     * @throws UserOrBookDoesNotExistException if a user or book is not found
     */
    public void loadLendings() throws IOException, UserOrBookDoesNotExistException {
        List<String[]> data = CSVUtils.readCSV(LENDINGS_FILE);
        lendings.clear();
        for (String[] row : data) {
            String bookTitle = row[0];
            String userName = row[1];
            LocalDate dueDate = LocalDate.parse(row[2]);
            Book book = findBookByTitle(bookTitle);
            User user = findUserByName(userName);
            if (book != null && user != null) {
                lendings.add(new Lending(book, user));
            }
        }
    }

    /**
     * Constructs a LibrarySystem with empty lists of books, users, and lendings.
     */
    public LibrarySystem() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        lendings = new ArrayList<>();
        try {
            loadStudents();
            loadFaculty();
            loadBooks();
            loadLendings();
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        } catch (UserOrBookDoesNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a book with a title and a list of authors to the library.
     *
     * @param title   the title of the book
     * @param authors the list of authors
     * @throws EmptyAuthorListException if the author list is empty
     */
    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException, IOException {
        if (authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty.");
        }
        books.add(new Book(title, authors));
        try {
            saveBooks();
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
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
        try {
            saveBooks();
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    /**
     * Adds a student user to the library system.
     *
     * @param name    the name of the student
     * @param feePaid whether the student has paid the fee
     */
    public void addStudentUser(String name, boolean feePaid) {
        users.add(new Student(name, feePaid));
        try {
            saveStudents();
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    /**
     * Adds a faculty member user to the library system.
     *
     * @param name       the name of the faculty member
     * @param department the department of the faculty member
     */
    public void addFacultyMemberUser(String name, String department) {
        users.add(new FacultyMember(name, department));
        try {
            saveFaculty();
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    /**
     * Retrieves all users in the library system.
     */
    public void getUsers() {
        for (User user : users) {
            if (user instanceof Student) {
                System.out.println("Student: " + user.getName() + ", Fee Paid: " + ((Student) user).isFeePaid());
            } else if (user instanceof FacultyMember) {
                System.out.println("Faculty Member: " + user.getName() + ", Department: " + ((FacultyMember) user).getDepartment());
            }
        }
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
     * Retrieves all books in the library system.
     */
    public void getBooks() {
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Authors: " + String.join(", ", book.getAuthors().stream().map(Author::getName).toList()));
        }
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
        try {
            saveLendings();
        } catch (IOException e) {
            System.out.println("Error saving lendings: " + e.getMessage());
        }
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
        try {
            saveLendings();
        } catch (IOException e) {
            System.out.println("Error saving lendings: " + e.getMessage());
        }
    }

    /**
     * Retrieves all lendings in the library system.
     */
    public void getLendings() {
        for (Lending lending : lendings) {
            System.out.println("Book: " + lending.getBook().getTitle() + ", User: " + lending.getUser().getName() + ", Due Date: " + lending.getDueDate());
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
                try {
                    saveLendings();
                } catch (IOException e) {
                    System.out.println("Error saving lendings: " + e.getMessage());
                }
                return;
            }
        }

        throw new UserOrBookDoesNotExistException("Book " + book.getTitle() + " or user " + user.getName() + " not found");
    }
}