public enum LevelOfTraining {
    NA, BASIC, INTERMEDIATE, ADVANCED;

    public String toString() {
        String prettyboi = "NA";
        switch (this) {
            case NA -> prettyboi = "no training at all";
            case BASIC -> prettyboi = "sit, stay, lay down on instruction";
            case INTERMEDIATE -> prettyboi = "play fetch, and will stop barking if instructed";
            case ADVANCED -> prettyboi = "perform tricks - handshake, play dead, jump over a bar";
        }
        return prettyboi;
    }
}
