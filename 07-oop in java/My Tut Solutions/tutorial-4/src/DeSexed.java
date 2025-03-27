/**
 * Enum for a dogs desexed status
 */
public enum DeSexed {
    YES, NO;

    /**
     * A tostring method that changes the enum values to correct case
     * @return title case of selected enum value
     */
    public String toString() {
        return switch (this) {
            case YES -> "Yes";
            case NO -> "No";
        };
    }

    /**
     * Changes the selected option to either true or false depending on the value selected
     * @return a boolean equivalent from the user input
     */
    public Boolean toBool() {
        return switch (this) {
            case YES -> true;
            case NO -> false;
        };
    }
}
