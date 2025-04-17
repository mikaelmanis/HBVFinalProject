
package is.hi.hbv202g.assignment8;

/**
 * Represents an author with a name.
 */
class Author {
    private String name;

    /**
     * Constructs an Author with the specified name.
     *
     * @param name the name of the author
     */
    public Author(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the author.
     *
     * @return the name of the author
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the author.
     *
     * @param name the new name of the author
     */
    public void setName(String name) {
        this.name = name;
    }
}
