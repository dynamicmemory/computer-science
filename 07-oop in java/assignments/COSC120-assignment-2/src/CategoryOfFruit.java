/**
 * @author - Joshua Hahn
 * Email - jhahn@myune.edu.au
 * created for COSC120 Assignment 2
 * A programed solution to address the issues faced when expanding the base functionality for "the greenie geek - a
 * Peter Pinkman experience"
 */

// an enum class that represents all the different categories of fruiting plants from the database
public enum CategoryOfFruit {
    POME, VINE, CITRUS, STONE_FRUIT;

    /**
     * @return a cute rendition of the corresponding enum class of fruit
     */
    public String toString() {
        return switch (this) {
            case POME -> "Pome";
            case VINE -> "Vine";
            case CITRUS -> "Citrus";
            case STONE_FRUIT -> "Stone Fruit";
        };
    }
}
