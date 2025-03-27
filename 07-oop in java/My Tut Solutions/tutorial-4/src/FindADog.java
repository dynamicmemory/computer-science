import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindADog {

    public static void main(String[] args) {
        System.out.println("Hello pup");

        // Creating an instance of this class to call its methods
        FindADog fn = new FindADog();
        // Creating an instance of the alldogs class to access its methods
        AllDogs ad = new AllDogs();
        // Assigning a dog call sog from the matchedDog method, passing in the users
        // requested dog and the set of dogs from the database
        Set<Dog> sog = ad.matchedDog(fn.userDreamDog(), fn.loadAllDogs().allDogs);

        // If the users dog matches a fog in the database, then they can choose to adopt
        // If they choose to adopt, they enter their details and it is output to a file
        // else I curse them.
        StringBuilder message = new StringBuilder();
        Map<String, Dog> dogMap = new HashMap<>();
        if (sog != null) {
            for (Dog dog : sog) {
                message.append(dog.getName()).append(" ").append(dog.getMicrochip()).append(" is a ").
                        append(dog.getAge()).append(" year old ").append(dog.getSex()).append(" ").
                        append(dog.getBreed()).append(". Desexed: ").append(dog.getDeSexed()).append("\n");

                dogMap.put(dog.getName() + " "+ dog.getMicrochip(), dog);
            }
            String choice = (String) JOptionPane.showInputDialog(null,"Enter the name of the dog youd like to adopt\n" + message,
                    null,3,null, dogMap.keySet().toArray(), " List of dog");

            if (choice!=null) {
                Person customer = fn.userInfo();
                fn.addContactInfo(customer, dogMap.get(choice));
            }
            else {
                JOptionPane.showMessageDialog(null, "You're heartless, I curse you and your" +
                        "family for 6 generations");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No pup matched you request");
        }

        // Print out and check that all dogs worked.
//        System.out.println(fn.loadAllDogs().allDogs);

    }

    /**
     * Reads a database of dog information, takes each line from the database and splits up
     *  the relevant dog information and sends it to the dog class to turn the information
     *  into dog objects, then appends those objects to a set and returns an AllDogs object
     *  of that set.
     * @return AllDogs object containing a set of dog objects converted from a database using
     * the dogs class.
     */
    public AllDogs loadAllDogs() {
        // Initiating an Alldogs object to add dog objects to from the database
        AllDogs allDogs = new AllDogs();

        // File path
        String filePath = "./allDogs.txt";
        Path path = Path.of(filePath);

        try {
            List<String> dogsFromFile = Files.readAllLines(path);
            // Iterating through every line in the database
            for (String line : dogsFromFile) {
                // Splitting up the string that is read in
                String[] dogDetails = line.split(",");

                // Check for the headings, and skip them
                if (dogDetails[0].equalsIgnoreCase("name")) {continue;}

                // assigning the split line to all the dog attributes
                String name = dogDetails[0];
                long microchip = Long.parseLong(dogDetails[1]);
                String sex = dogDetails[2];

                // Assigning true or false to the yes or no answer
                boolean deSexed = dogDetails[3].equalsIgnoreCase("yes");
                int age = Integer.parseInt(dogDetails[4]);
                String breed = dogDetails[5];

                Dog dog = new Dog(name, microchip, sex, deSexed, age, breed);

                allDogs.addDog(dog);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return allDogs;
    }

    /**
     * Asks the user a series of questions about a dog to find out what there dream dog looks like and creates a
     * dog object from all the user inputs to later use to compare against the database
     * @return a dog object of all the users dream traits
     */
    public Dog userDreamDog(){
        JOptionPane.showMessageDialog(null,"Welcome to adopt a pup, may I please take your order");

        DogBreed dogBreed = (DogBreed) JOptionPane.showInputDialog(null,
                "Wat breed of pup you lookin` for?", null, 3, null, DogBreed.values(),
                DogBreed.CHIHUAHUA);
        if (dogBreed == null) System.exit(0);

        // Asks user for which sex they are looking for, gives options from sex enum
        Sex sex = (Sex) JOptionPane.showInputDialog(null, "Male or Female pup?",
                null,3,null,Sex.values(), Sex.MALE);
        if (sex == null) {System.exit(0);}

        // Asks user for desexed option, gives options from the desexed enum
        DeSexed deSexed = (DeSexed) JOptionPane.showInputDialog(null,"Desexed? yes or no",
                null,3,null,DeSexed.values(), DeSexed.YES);
        if (deSexed == null) {System.exit(0);}

        int min = -1;
        int max = 20;
        while (min < 0 && max > 19) {
            try {
            min = Integer.parseInt(JOptionPane.showInputDialog("Enter the minimum age for a pup between 1 and 20"));
            max = Integer.parseInt(JOptionPane.showInputDialog("Enter the Maximum age for a pup between 1 and 20"));
            }

            catch (NumberFormatException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 20");
            }
        }

        return new Dog(dogBreed.toString(), sex.toString(), deSexed.toBool(), min, max);

    }

    /**
     * Gathers all the neccessary information on the user if they choose to adopt a puppy from the database.
     * @return a person object that contains the users name, email, phone number.
     */
    public Person userInfo() {
        String name = JOptionPane.showInputDialog("Pleas enter your first and last name");

        // Do while loop for email input to control the users input to correct email format
        String email;
        do {
            email = JOptionPane.showInputDialog("Please enter your email address");
            if (email==null) System.exit(0);}
        while (!isValidEmail(email));

        // Do while loop for user phone number input, using regex to control the input to correct format.
        String number;
        do {
            number = JOptionPane.showInputDialog("Please enter your phone number");
            if (number==null) System.exit(0);}
        while (!isValidPhoneNumber(number));

        return new Person(name, email, number);
    }

    /**
     * If the user request to adopt a dog, they are required to fill in information about themselve
     * this method taks that information as well as the dof they wish to adopt and it saves that
     * information ato a dile to be contacted later on about the dog
     * @param p - A Person object containing the name, email and number of the customer
     * @param d - A dog object that matches one of the dogs in our database
     */
    public void addContactInfo(Person p, Dog d) {
        List<String> names = List.of(p.name.split(" "));
        String pupDetails = d.getName() + " ("+d.getMicrochip()+"). ";
        String message = names.get(0) + " " + names.get(1) + " wishes to adopt " + pupDetails +
                "Their phune number is " + p.number + " and their email address is: " + p.email;

        File newFile = new File(names.get(0) + "_" + names.get(1) + "_adoption_request.txt");

        try {
            FileWriter write = new FileWriter(newFile);
            write.write(message);
            write.close();
        }
        catch (IOException e) {
            System.out.println("SystemERROR. SystemERROR. SystemERROR. SystemERROR. SystemERROR. " +
                    "SELF DESTRUCT IN 5...4...3...2...");
        }

        JOptionPane.showMessageDialog(null,"Thank you!, You adoption request has been " +
                "submitted. One of our friendly staff will be in touch shortly.");
    }

    /**
     * Takes a string input from the user in the form of their phone number and returns either true or false
     * depending on if it matches our formatting requirements
     * @param number String, users phone number
     * @return boolean, either true or false
     */
    public static boolean isValidPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * Check the validity of a users email input to make sure it matches our formatting
     * @param email - String, users input
     * @return true or false depending on the users input.
     */
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._]+\\.[a-zA-Z]{2,6}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
