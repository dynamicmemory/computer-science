public enum Hair {
    NA, HAIRLESS;

    public String toString() {
        return switch(this) {
            case NA -> "No";
            case HAIRLESS -> "Yes";
        };
    }
}
