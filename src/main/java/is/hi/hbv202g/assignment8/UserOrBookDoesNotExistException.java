package is.hi.hbv202g.assignment8;

/**
 * Exception thrown when a user or book does not exist in the library system.
 */
class UserOrBookDoesNotExistException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message
     */
    public UserOrBookDoesNotExistException(String message) {
        super(message);
    }
}
