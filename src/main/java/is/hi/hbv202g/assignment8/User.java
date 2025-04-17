
package is.hi.hbv202g.assignment8;

/**
 * Represents a user in the library system.
 */
class User {
    protected String name;

    /**
     * Constructs a User with the specified name.
     *
     * @param name the name of the user
     */
    public User(String name) {
        this.name = name;

    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }
}
