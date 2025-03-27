/**
 * Enum for choosing the sex of the dog
 */

public enum Sex {
    // Geneder options
    FEMALE, MALE;

    public String toString() {
        return switch (this) {
            case FEMALE -> "Female";
            case MALE -> "Male";
        };
    }
}
