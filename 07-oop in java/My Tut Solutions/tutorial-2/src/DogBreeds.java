import java.util.Locale;
import java.util.Scanner;

/**
 * Short practice with using for loops to display elements in an array
 */

public class DogBreeds {
    /**
     * Just a few exercises for learning and practising loops in java
     * @param args none required
     */
    public static void main(String[] args) {
        // Creating an array of data to use for practising loops in java
        String[] dogBreeds = {"bulldog", "dalmatian", "jack russell terrier", "moodle", "Yorkshite terrier",
                "golden retriever", "BULL terrier", "pomeranion","pit bull terrier", "Siberian husky"};


        // iterates through all strings in the array and changes all chars to lowercase
        for (int i=0;i<dogBreeds.length;i++) {
            String tempDogName = dogBreeds[i];
            dogBreeds[i] = tempDogName.toLowerCase();
        }

        // prints out items in the array
        System.out.println("********ALL BREEDS*********");
        for (String doggies : dogBreeds) {
            System.out.println(doggies);
        }

        // selecting specific elements in an array and printing
        System.out.println("\n********TERRIERS*********");
        for (String doggies : dogBreeds) {
            // Using nested if statements to require the specifically asked for output
            if (doggies.contains("terrier")) {
                if (!doggies.contains("pit bull terrier")) {
                    System.out.println(doggies);
                }
            }
        }
    }
}
