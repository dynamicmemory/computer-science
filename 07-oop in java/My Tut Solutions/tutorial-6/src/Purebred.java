public enum Purebred {
    YES, NO, NA;

    public String toString() {
        String prettyboi = "Select";
        switch (this) {
            case YES -> prettyboi = "Yes";
            case NO -> prettyboi = "No";
            case NA -> prettyboi = "I don't mind";
        }
        return prettyboi;
    }

}
