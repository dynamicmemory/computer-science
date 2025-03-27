/**
 * @author - Joshua Hahn
 * Email - jhahn@myune.edu.au
 * created for COSC120 Assignment 2
 * A programed solution to address the issues faced when expanding the base functionality for "the greenie geek - a
 * Peter Pinkman experience"
 */

import java.util.*;

public class Inventory {

    // A set of fruiting plants that hold all the options from the database
    private final Set<FruitingPlant> inventory = new HashSet<>();

    /**
     *
     * @param fruitingPlant adds a plant item from the database to the set of all fruiting plants
     */
    public void addItem(FruitingPlant fruitingPlant){
        this.inventory.add(fruitingPlant);
    }

    /**
     * Takes the users dream plant, calls the comparison method and compares plant prices to user input, if
     * it is a match it is added to a list to be return for the use of processing a users choice
     * @param dreamFruitingPlant - a users dream plant
     * @return Matching - a list containing all plants from the database that match the users inputs.
     */
    public List<FruitingPlant> findMatch(DreamFruitingPlant dreamFruitingPlant){
        // Initialise a list that will be used to hold all matching plants from the database
        List<FruitingPlant> matching = new ArrayList<>();

        // Iterate through all plants in the database for comparison
        for(FruitingPlant fruitingPlant : inventory){
            // Call the comparison function from the dream Fruiting class, if it returns false, move onto the next plant
            if (!fruitingPlant.dreamFruitingPlant().compareDreamPlants(dreamFruitingPlant)) continue;

            // Initialise a LinkedHashMap, to retain order, this will be used to map the pot sizes to price from the db
            Map<Integer, Float> correspondingPriceMap = new LinkedHashMap<>();

            // Get a hold of the Pot-size-to-price-map from our main plant map, turn it into a string, clean off the unwanted
            // characters and create an array of strings, splitting on the comma.
            String[] potSizePriceList = fruitingPlant.dreamFruitingPlant().getFilter(Filters.POT_SIZE_PRICE).toString()
                    .replace("{", "").replace("}","").split(",");

            // Next, for every string in the potSizePriceList, create another String array, splitting on the equals sign
            // and put both the first and second element into the map, parsing them as int and float. You now have control
            // of your pot size to price map again
            for(String potPrices : potSizePriceList) {
                String[] temp = potPrices.trim().split("=");
                correspondingPriceMap.put(Integer.parseInt(temp[0]), Float.parseFloat(temp[1]));
            }
            // Pass the users requested pot size into the newly created map retrieving from it the price fot that pot size
            Float correspondingPrice = correspondingPriceMap.get((int) dreamFruitingPlant.getFilter(Filters.POT_SIZE));
            // If the price for the plant at the users specified pot size is less then the min requested or greater than
            // the max price specified, move onto the next plant, otherwise add it to our list of matching plants.
            if(correspondingPrice < dreamFruitingPlant.getMinPrice() || correspondingPrice > dreamFruitingPlant.getMaxPrice()) continue;
            matching.add(fruitingPlant);
        }
        return matching;
    }

    /**
     * Pass in a category of fruiting plant and a specific feature and create a new set of all the options for that
     * feature if the fruit matches the user's choice. This is used to generate dropdown lists for the "types",
     * "pollinators" and "trellis" features. We use a set to prevent duplicates from appearing. Any changes
     * to these features in the future will automatically be updated via this method.
     * @param category A string of the users chosen category of plant
     * @return A set comprised of all the features from the passed in features filter
     */
    public Set<String> getAllOptions(String category, Filters feature){
        // Initialise a set for any group of attributes
        Set<String> allOptions = new LinkedHashSet<>();

        // Iterate through every plant in the database
        for(FruitingPlant fruitingPlant : inventory){
            //if the plant matches the passed in category, ie the fruit the user has chosen matches the fruit of an
            // entry in the database,
            if (fruitingPlant.dreamFruitingPlant().getFilter(Filters.CATEGORY).equals(category)) {
                // if the feature passed in is NOT an instance of a collection (list, set, etc)
                if (!(fruitingPlant.dreamFruitingPlant().getFilter(feature) instanceof Collection<?>)) {
                    // add the feature to the set
                    allOptions.add(fruitingPlant.dreamFruitingPlant().getFilter(feature).toString());
                    // Otherwise if it is a collection
                } else {
                    // Create an array of string and get the string of the passed in features collection, split on the comma
                    String[] featureList = fruitingPlant.dreamFruitingPlant().getFilter(feature).toString().split(",");

                    // for every string in the collection
                    for(String feat : featureList) {
                        // Clean the data and add it to the set
                        allOptions.add(feat.replace("]","").replace("[","").trim());
                    }
                }
            }
        }
        // Add the option for skipping
        allOptions.add("SKIP (any will do)");

        // return all the options for that feature
        return allOptions;
    }

}
