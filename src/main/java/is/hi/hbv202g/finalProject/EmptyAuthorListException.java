package is.hi.hbv202g.finalProject;

/**
 * Exception thrown when an operation involving a list of authors is attempted
 * but the list is empty.
 */
class EmptyAuthorListException extends Exception {

    /**
     * Constructs an EmptyAuthorListException with the specified detail message.
     *
     * @param message the detail message
     */
    public EmptyAuthorListException(String message) {
        super(message);
    }
}
