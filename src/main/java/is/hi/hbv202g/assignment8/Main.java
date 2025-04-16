package is.hi.hbv202g.assignment8;

import java.time.LocalDate;

/**
 * The entry point of the library system application.
 */
public class Main {

    /**
     * The main method to initialize and run the library system.
     *
     * @param args command-line arguments (not used)
     * @throws UserOrBookDoesNotExistException if a user or book is not found during operations
     * @throws EmptyAuthorListException        if an attempt is made to add a book with an empty author list
     */
    public static void main(String[] args) throws UserOrBookDoesNotExistException, EmptyAuthorListException {
        LibrarySystem myLibrarySystem = new LibrarySystem();
    }
}