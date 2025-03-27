import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class AdoptionRequest {

    public static void main(String[] args) {
        AdoptionRequest ar = new AdoptionRequest();

        Map<String, Dog> dogDataFromTxt = new HashMap<>();
        dogDataFromTxt = ar.loadDogData();

        // Set dog with microschip number "989343556" to desexed
        Dog dog = (Dog) dogDataFromTxt.get("989343556");
        dog.setDeSexed(true);

        // IMPORTANT BLOCK TO STUDY
        // Creating a new set of dog from out map to pass into all the comparison methods
//        The method I thought of
//        Set<Dog> setOfDogs = new HashSet<>();
//        for (Dog god : dogDataFromTxt.values()) {
//            setOfDogs.add(god);
//        }

        // Javas first suggestion for optimization
//        Set<Dog> setOfDogs = new HashSet<>();
//        setOfDogs.addAll(dogDataFromTxt.values());

        // Javas second suggestions for optimization
        Set<Dog> setOfDogs = new HashSet<>(dogDataFromTxt.values());

        // Assigning the users input of their dream dog to a new dog object to pass into our comparison methods
        Dog usersDogRequest = ar.getUserInput();


        // Matching the dog breeds from available dogs to user defined breed
        System.out.println("Dogs with matching breed:");
        Set<Dog> narrowedSet = ar.isBreedSame(setOfDogs, usersDogRequest);
        for (Dog god : narrowedSet){
            System.out.println(god.getName() + " " + god.getMicrochip() + " is " + god.age() + " years old. De-sexed:" +
                    god.isDeSexed() + ". Sex: " + god.getSex());
        }
        // Matching the dog breeds and age ranges
        System.out.println("Dogs with matching breed, within age range:");
        narrowedSet = ar.isMatchAgeRange(narrowedSet, usersDogRequest.minAge, usersDogRequest.maxAge);
        for (Dog god : narrowedSet){
            System.out.println(god.getName() + " " + god.getMicrochip() + " is " + god.age() + " years old. De-sexed:" +
                    god.isDeSexed() + ". Sex: " + god.getSex());
        }
        // Matching dog breeds, age ranges and sexes
        System.out.println("\nDogs with matching breed, within age range with matching sex");
        narrowedSet = ar.isSexSame(narrowedSet, usersDogRequest);
        for (Dog god : narrowedSet){
            System.out.println(god.getName() + " " + god.getMicrochip() + " is " + god.age() + " years old. De-sexed:" +
                    god.isDeSexed() + ". Sex: " + god.getSex());
        }
        // Matching dog breeds, age ranges and sexes and desexed
        System.out.println("\nDogs with matching all criteria");
        narrowedSet = ar.isDeSexedSame(narrowedSet, usersDogRequest);
        for (Dog god : narrowedSet){
            System.out.println(god.getName() + " " + god.getMicrochip() + " is " + god.age() + " years old. De-sexed:" +
                    god.isDeSexed() + ". Sex: " + god.getSex());
        }
    }

    /**
     * This Method reads a file of dog information: breed, name, chip number,etc and parses it into
     * a list of individual dogs. It then splits up the information for each dog and sends the information
     * into the Dog class and creates a new Dog object for each line of the file, filling in the needed
     * info, it then places that Dog object into a Hashmap as a value with the dogs chip number as the
     * value.
     *
     */
    public Map<String, Dog> loadDogData() {
        // Lines to get the file located and initialised
        String filepath = "./allDogs.txt";
        Path path = Path.of(filepath);

        // The hashmap for all the dog objects is created here
        Map<String, Dog> mapOfAllDogs = new HashMap<>();

        try {
            // Reading in the file
           List<String> dogsOnFile = Files.readAllLines(path);

           // Iterate through every line in the file.
           for (String nextDog : dogsOnFile) {
               // Split up all the info at the commas
               String[] dogInformation = nextDog.split(",");

               // Throw away the titles of each column
               if (dogInformation[0].equalsIgnoreCase("name")){ continue;}

               // Assigning all needed values
               String name = dogInformation[0];
               long chip = Long.parseLong(dogInformation[1]);
               String sex = dogInformation[2];
               boolean deSexed = dogInformation[3].equalsIgnoreCase("yes");
               int age = Integer.parseInt(dogInformation[4]);
               String breed = dogInformation[5];

               // Call the Dog constructor
               Dog dog = new Dog(name, chip, breed, sex, deSexed, age);

               // Send the pup to the map
               mapOfAllDogs.put(dogInformation[1], dog);
           }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        // Print the map to the standard output to make sure all went well.
//        System.out.println(mapOfAllDogs);
        return mapOfAllDogs;
    }

    /**
     * Asks user a series of questions regarding what type of dog they are looking to adopt
     * creates a dog object with those wants and returns that dog object
     */
    public Dog getUserInput() {

        // Welcome user
        JOptionPane.showMessageDialog(null, "Welcome to adopt a dog, dawg");

        // Ask for breed
        String breed = JOptionPane.showInputDialog("What breed are you looking for");
        if (breed == null) {exitOnNull();}

        // Ask for sex
        String sex = JOptionPane.showInputDialog("What sex are you looking for (male or female)");
        if (sex == null) {exitOnNull();}

        //Ask for deSexed
        boolean deSexed = false;
        String deSexedQuestion = JOptionPane.showInputDialog("Would you like the pup to be desexed or not (yes or no)");
        if (deSexedQuestion == null) {exitOnNull();}
        else if (deSexedQuestion.equalsIgnoreCase("yes")) {deSexed = true;}

        //Ask for age range
        int minAge =-1;

        while (minAge < 0 || minAge > 20){
            try {
                minAge = Integer.parseInt(JOptionPane.showInputDialog("What is the minimum age you would like"));
            }
            catch (Exception e){
                System.out.println(e);
            }
        }

        int maxAge =-1;
        while (maxAge < minAge || maxAge > 20){
            try {
                maxAge = Integer.parseInt(JOptionPane.showInputDialog("What is the minimum age you would like"));
            }
            catch (Exception e){
                System.out.println(e);
            }
        }

        return new Dog(breed, sex, deSexed, minAge, maxAge);
    }

    /**
     * Compares aset of dogs passed in, to a user defined dog and returns a list of dogs that match the users
     * dog if their breeds are the same
     * @param dogList a set of dogs
     * @param dog a user defined dog
     * @return a new set of dogs that match the criteria
     */
    public Set<Dog> isBreedSame(Set<Dog> dogList, Dog dog) {
        Set<Dog> newSetOfDogs = new HashSet<>();

        for (Dog god : dogList) {
            if (dog.compareBreed(god)) {newSetOfDogs.add(god);}
        }
        return newSetOfDogs;
    }

    /**
     * Compares aset of dogs passed in, to a user defined dog and returns a list of dogs that match the users
     * dog if their sex are the same
     * @param dogList a set of dogs
     * @param dog a user defined dog
     * @return a new set of dogs that match the criteria
     */
    public Set<Dog> isSexSame(Set<Dog> dogList, Dog dog) {
        Set<Dog> newSetOfDogs = new HashSet<>();

        for (Dog god : dogList) {
            if (dog.compareSex(god)) {newSetOfDogs.add(god);}
        }
        return newSetOfDogs;
    }

    /**
     * Compares every dog in a list against a min and max age provided by the user to determine if any dogs
     * that are available to adopt fall within the desired age range.
     * @param dogList a list of dogs
     * @param min an int for the minimum age of a dog
     * @param max an int for the maximum age of at dog
     * @return a new list of dogs theat meet the critea
     */
    public Set<Dog> isMatchAgeRange(Set<Dog> dogList, int min, int max) {
        Set<Dog> newSetOfDogs = new HashSet<>();

        for (Dog god : dogList) {
            if (god.ageBetween(min, max)) {
                newSetOfDogs.add(god);
            }
        }
        return newSetOfDogs;
    }

    public Set<Dog> isDeSexedSame(Set<Dog> dogList, Dog dog){
        Set<Dog> newSetOfDogs = new HashSet<>();

        for(Dog god : dogList) {
            if (dog.compareDeSexed(god)) {newSetOfDogs.add(god);}
        }
        return newSetOfDogs;
    }

    private static void exitOnNull(){
        JOptionPane.showMessageDialog(null,"Sorry, have a nice day");
        System.exit(0);
    }
}

