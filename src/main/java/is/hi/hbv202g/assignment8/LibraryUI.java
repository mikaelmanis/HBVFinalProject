package is.hi.hbv202g.assignment8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The user interface for the Library System.
 * Provides menus and handles user input for managing users, books, and lendings.
 */
public class LibraryUI {
    private LibrarySystem librarySystem;
    private Scanner scanner;
    private boolean running;

    /**
     * Constructs a LibraryUI and initializes the LibrarySystem and Scanner.
     */
    public LibraryUI() {
        librarySystem = new LibrarySystem();
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu of the library system.
     */
    public void startMenu() {
        System.out.println("Welcome to the Library System!");
        System.out.println("1. User Menu");
        System.out.println("2. Book Menu");
        System.out.println("3. Lending Menu");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    /**
     * Displays the user menu for managing users.
     */
    public void userMenu() {
        System.out.println("User Menu:");
        System.out.println("1. Add Student User");
        System.out.println("2. Add Faculty User");
        System.out.println("3. List Users");
        System.out.println("4. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    /**
     * Displays the book menu for managing books.
     */
    public void bookMenu() {
        System.out.println("Book Menu:");
        System.out.println("1. Add Book with Title and Author List");
        System.out.println("2. Add Book with Title and Single Author");
        System.out.println("3. List Books");
        System.out.println("4. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    /**
     * Displays the lending menu for managing lendings.
     */
    public void lendingMenu() {
        System.out.println("Lending Menu:");
        System.out.println("1. Lend Book");
        System.out.println("2. Return Book");
        System.out.println("3. List Lendings");
        System.out.println("4. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    /**
     * Starts the library system and handles the main menu navigation.
     *
     * @throws UserOrBookDoesNotExistException if a user or book is not found during operations
     */
    public void start() throws UserOrBookDoesNotExistException {
        running = true;
        while (running) {
            clearConsole();
            startMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    clearConsole();
                    userMenu();
                    handleUserMenu();
                    break;
                case 2:
                    clearConsole();
                    bookMenu();
                    handleBookMenu();
                    break;
                case 3:
                    clearConsole();
                    lendingMenu();
                    handleLendingMenu();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the Library System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles user menu options based on user input.
     */
    private void handleUserMenu() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter student name: ");
                String studentName = scanner.nextLine();
                System.out.print("Has the student paid the fee? (true/false): ");
                boolean feePaid = scanner.nextBoolean();
                librarySystem.addStudentUser(studentName, feePaid);
                System.out.println("Student user added successfully.");
                break;
            case 2:
                System.out.print("Enter faculty member name: ");
                String facultyName = scanner.nextLine();
                System.out.print("Enter department: ");
                String department = scanner.nextLine();
                librarySystem.addFacultyMemberUser(facultyName, department);
                System.out.println("Faculty member user added successfully.");
                break;
            case 3:
                System.out.println("Listing all users:");
                librarySystem.getUsers();
                break;
            case 4:
                System.out.println("Returning to Main Menu.");
                clearConsole();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * Clears the console output.
     */
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Handles book menu options based on user input.
     */
    private void handleBookMenu() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter book title: ");
                String bookTitle = scanner.nextLine();
                System.out.print("Enter author names (comma-separated): ");
                String authorNames = scanner.nextLine();
                String[] authorsArray = authorNames.split(",");
                List<Author> authors = new ArrayList<>();
                for (String authorName : authorsArray) {
                    authors.add(new Author(authorName.trim()));
                }
                try {
                    librarySystem.addBookWithTitleAndAuthorList(bookTitle, authors);
                    System.out.println("Book added successfully.");
                } catch (EmptyAuthorListException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                System.out.print("Enter book title: ");
                String singleAuthorBookTitle = scanner.nextLine();
                System.out.print("Enter author name: ");
                String singleAuthorName = scanner.nextLine();
                try {
                    librarySystem.addBookWithTitleAndNameOfSingleAuthor(singleAuthorBookTitle, singleAuthorName);
                    System.out.println("Book added successfully.");
                } catch (EmptyAuthorListException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                System.out.println("Listing all books:");
                librarySystem.getBooks();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * Handles lending menu options based on user input.
     *
     * @throws UserOrBookDoesNotExistException if a user or book is not found during operations
     */
    public void handleLendingMenu() throws UserOrBookDoesNotExistException {
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter user name: ");
                User userName = librarySystem.findUserByName(scanner.nextLine());
                System.out.print("Enter book title: ");
                Book bookTitle = librarySystem.findBookByTitle(scanner.nextLine());
                try {
                    librarySystem.borrowBook(userName, bookTitle);
                    System.out.println("Book lent successfully.");
                } catch (UserOrBookDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.print("Enter user name: ");
                User returnUserName = librarySystem.findUserByName(scanner.nextLine());
                System.out.print("Enter book title: ");
                Book returnBookTitle = librarySystem.findBookByTitle(scanner.nextLine());
                try {
                    librarySystem.returnBook(returnUserName, returnBookTitle);
                    System.out.println("Book returned successfully.");
                } catch (UserOrBookDoesNotExistException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                System.out.println("Listing all lendings:");
                librarySystem.getLendings();
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}