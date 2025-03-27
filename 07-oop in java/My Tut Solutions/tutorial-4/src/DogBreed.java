/**
 * enum class to hold all possible dog breeds for users to slecet from
 */

public enum DogBreed {
    DALMATIAN, LABRADOR, JACKRUSSELL, BULLDOG, PITBULL, ROTTWEILER, MAREMA, WHIPPET, GERMANSHEPARD, CHIHUAHUA,
    GOLDENRETRIEVER;

    /**
     * Changes the values of the enum to corrrect title case
     * @return
     */
    public String toString() {
        return switch (this) {
            case DALMATIAN -> "Dammatian";
            case LABRADOR -> "Labrador";
            case JACKRUSSELL -> "Jack Russell";
            case BULLDOG -> "Bull Dog";
            case PITBULL -> "Pit Bull";
            case ROTTWEILER -> "Rottweiler";
            case MAREMA -> "Marema";
            case WHIPPET -> "Whippet";
            case GERMANSHEPARD -> "German Shepard";
            case CHIHUAHUA -> "Chihuahua";
            case GOLDENRETRIEVER -> "Golden Retriever";
        };
    }

}
