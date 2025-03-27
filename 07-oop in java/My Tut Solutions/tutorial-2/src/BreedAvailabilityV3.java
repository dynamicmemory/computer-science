import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Breed 3 electric boogaloo... yeah that works
 */

public class BreedAvailabilityV3 {
    /**
     * Importing data in from a txt file and performing some operations with it using maps for practice, in particular
     * hashmaps.... hashmaps? hashmaps?!!!? anyway, same as the last set of q's just with maps and not sets.
     * @param args none required
     */
    public static void main(String[] args) {
        // Declaring file path for data
        String filePath = "./availableBreeds.txt";
        Path path = Path.of(filePath);

        // Try catch to read data source
        try {
            String fileContents = Files.readString(path);
            String[] arrOfBreeds = fileContents.toLowerCase().split(",");

            // Creates a hashmap and iterates through the above String array and adds each breed
            // to the hashmap, if the breed already exists, it will increment its value by 1
            Map<String, Integer> availableBreedsMap = new HashMap<>();
            for (String s : arrOfBreeds) {
                if (!availableBreedsMap.containsKey(s)) {
                    availableBreedsMap.put(s,1);
                }
                else {
                    availableBreedsMap.put(s, availableBreedsMap.get(s) + 1);
                }
            }

            // Print hashmap to standard output
            System.out.println(availableBreedsMap);

            // Add grey hound and increment dalmation by +1
            availableBreedsMap.put("grey hound", 1);
            availableBreedsMap.put("dalmatian", availableBreedsMap.get("dalmatian") +1);

            // Save rotweilers value
            int rotty = availableBreedsMap.get("rotweiler");
            // Remove rotweiler
            availableBreedsMap.remove("rotweiler");
            // add rotweiler with correct spelling appending previous value to it
            availableBreedsMap.put("rottweiler", rotty);

            System.out.println(availableBreedsMap);

            // Asks user which dog they would like ot adopt
            String pupRequest = JOptionPane.showInputDialog("Which breed of dog would you like to adopt");

            // conditionatl on if dog exist inside hashmap
            if (availableBreedsMap.containsKey(pupRequest.toLowerCase())) {
                // If it exists, ask if they would like to adopt
                String adoptRequest = JOptionPane.showInputDialog("Pup available, would you like to adopt? (yes/no)");
                // If yes, decrement value of pup in hashmap, or remove pup completely if there was only 1
                if (adoptRequest.equalsIgnoreCase("yes")){
                    if (availableBreedsMap.get(pupRequest) == 1) {
                        availableBreedsMap.remove(pupRequest);
                    }
                    else {
                        availableBreedsMap.put(pupRequest, availableBreedsMap.get(pupRequest) - 1);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Thank you for your enquiry");
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Sorry we do not have any of that breed to adopt currently");
            }

            System.out.println(availableBreedsMap);
        }
        catch (IOException e) {
            System.out.println(e + "File does not exist");
        }
    }
}
