public enum Sex {

    MALE, FEMALE;

    public String toString(){
        return switch (this) {
            case MALE -> "Male";
            case FEMALE -> "Female";
        };
    }
}
