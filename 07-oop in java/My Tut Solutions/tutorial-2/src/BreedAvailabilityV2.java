import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.StreamSupport;

/**
 * Using a file as input, playing around with collections and learning their attributes
 */

public class BreedAvailabilityV2 {
    /**
     * Reading in data, moiving it around to different collections to practice as well
     * as discover certain attributes.
     * @param args none required
     */
    public static void main(String[] args) {

        // Path of file to open
        String filePath = "./availableBreeds.txt";
        Path path = Path.of(filePath);

        // Try catch that contains file open plus setting all chars to lowercase and splitting them at commas
        try {
            String fileContents = Files.readString(path);
            String[] arrOfDogBreeds = fileContents.toLowerCase().split(",");

            // For each to check the contents to see if it is desired format
//            for (String dogs : arrOfDogBreeds) {
//                System.out.println(dogs);
//            }

            // Creating an array list and adding the contents of the string array to the ArrayList
            List<String> availableBreeds = new ArrayList<>();
            for (String s : arrOfDogBreeds) {
                availableBreeds.add(s);
                // Can just use add all to optimise this code
            }

            // Print the content of the arrayList to standard output
            for (String dog: availableBreeds){
                System.out.println(dog);
            }

            // Adding elements to the arraylist and printing content to standard output
            availableBreeds.add("dalmatian");
            availableBreeds.add("grey hound");
            for (String dog: availableBreeds){
                System.out.println(dog);
            }

            // Using set to change the spelling of an specificied element
            availableBreeds.set(1, "rottweiler");
            for (String s:availableBreeds){
                System.out.println(s);
            }

            // Sorting the arrayList alphabetically
            System.out.println("\n\n");
            Collections.sort(availableBreeds);
            for(String s : availableBreeds) {
                System.out.println(s);
            }

            // Creating a hashset of the data and printing both to standard output to see the difference
            // Hashsets do not have duplicates
            Set<String> availableBreedsHashSet = new HashSet<>(availableBreeds);
            System.out.println(availableBreeds);
            System.out.println(availableBreedsHashSet);

            // Creating a treeset and seeing the differences
            // Treesets have no duplicates and are sorted alphabetically
            Set<String> availableBreedsTreeSet = new TreeSet<>(availableBreeds);
            System.out.println(availableBreedsTreeSet);

        }
        catch (IOException e) {
            System.out.println(e + " File does not exist");
        }
    }
}
