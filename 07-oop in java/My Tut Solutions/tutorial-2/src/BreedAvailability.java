import java.util.Arrays;
import java.util.Scanner;

/**
 * Practicing various tasks using arrays
 */

public class BreedAvailability {
    /**
     * Asks user for there favourite dog breed and does things once they reply
     * Sort array
     * Print array
     */
    public static void main(String[] args) {
        // Create a array of dog breeds
        String[] dogBreeds = {"Dalmatian", "Rottweiler", "German Sheppard", "Jack Russell terrier", "Wolfhound",
        "Siberian Husky", "Chihuahua", "Moodle", "Poodle", "Labrador", "Maremma"};

        // Initiate an array of users fav dog breeds
        String[] usersFav = new String[5];

        // Create a scanner object to accept users fav dog breeds
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your five favourite dog breeds one at a time:");

        // Loop 5 times to get the users five fav dog breeds
        for (int i=0;i<usersFav.length;i++) {
            String addFav = scanner.nextLine();
            usersFav[i] = addFav;
        }

        // Iterate through both arrays to find any matches and respond to user if any are found
        for (int i=0;i<usersFav.length; i++){
            for (int j=0;j<dogBreeds.length;j++){
                if (usersFav[i].equalsIgnoreCase(dogBreeds[j])){
                    System.out.println(usersFav[i] +" Is available to adopt");
                }
            }
        }

        Arrays.sort(dogBreeds);
        System.out.println(Arrays.toString(dogBreeds));
    }
}
