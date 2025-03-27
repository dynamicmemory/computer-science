public enum TypeOfPet {
    DOG, CAT, GUINEA_PIG;

    public String toString() {
        return switch (this) {
            case DOG -> "Dog";
            case CAT -> "Cat";
            case GUINEA_PIG -> "Guinea Pig";
        };
    }
}
