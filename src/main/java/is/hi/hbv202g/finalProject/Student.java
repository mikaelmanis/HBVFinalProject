package is.hi.hbv202g.finalProject;

/**
 * Represents a student user in the library system.
 */
class Student extends User {
    private boolean feePaid;

    /**
     * Constructs a Student with the specified name and fee payment status.
     *
     * @param name    the name of the student
     * @param feePaid whether the student has paid the fee
     */
    public Student(String name, boolean feePaid) {
        super(name);
        this.feePaid = feePaid;
    }

    /**
     * Checks if the student has paid the fee.
     *
     * @return true if the fee is paid, false otherwise
     */
    public boolean isFeePaid() {
        return feePaid;
    }

    /**
     * Sets the fee payment status of the student.
     *
     * @param feePaid the new fee payment status
     */
    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }
}