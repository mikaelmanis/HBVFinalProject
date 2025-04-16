package is.hi.hbv202g.assignment8;

/**
 * Represents a faculty member who is a user with an associated department.
 */
class FacultyMember extends User {
    private String department;

    /**
     * Constructs a FacultyMember with the specified name and department.
     *
     * @param name       the name of the faculty member
     * @param department the department of the faculty member
     */
    public FacultyMember(String name, String department) {
        super(name);
        this.department = department;
    }

    /**
     * Returns the department of the faculty member.
     *
     * @return the department of the faculty member
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the faculty member.
     *
     * @param department the new department of the faculty member
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}