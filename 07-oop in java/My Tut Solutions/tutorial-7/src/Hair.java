

public enum Hair {
    HAIRLESS, SHORT, LONG, NA;

    public String toString(){
        return switch (this) {
            case HAIRLESS -> "Hairless";
            case SHORT -> "Short";
            case LONG -> "Long";
            default -> "NA";
        };
    }

}