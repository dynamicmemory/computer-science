import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindAPet {

    private static final String filePath = "./allPets.txt";
    private static final String appName = "Poop";
    private static AllPets allPets;

    public static void main(String[] args) {
    allPets = loadPets();
    // aware this needs input protection but dont care this second
    String type = JOptionPane.showInputDialog("You want cat or dog bruv?");
    DreamPet dreamPet = getUserCriteria(type);
    processSearchResults(dreamPet);

    }

    private static AllPets loadPets() {
        AllPets allPets = new AllPets();
        Path path = Path.of(filePath);

        List<String> petData = null;
        try {
            petData = Files.readAllLines(path);
        } catch (IOException e){
            System.out.println("Your shit broke dawg");
        }
        // Improvement for assignment could be wrapping this whole hting in a try catch and just output problem with data
        for (String line : petData) {
            String[] element = line.split(",");
            String type = element[0];
            String name = element[1];
            long microchip = Long.parseLong(element[2]);
            Sex sex = Sex.valueOf(element[3]);
            DeSexed deSexed = DeSexed.valueOf(element[4]);
            int age = Integer.parseInt(element[5]);
            String breed = element[6];
            Purebred purebred = Purebred.valueOf(element[7]);
            float adoptionFee = Float.parseFloat(element[8]);
            Hair hairless = Hair.valueOf(element[9]);
            LevelOfTraining training = LevelOfTraining.valueOf(element[10]);
            int exercise = Integer.parseInt(element[11]);

            if (type.equalsIgnoreCase("cat")) {
                DreamCat dreamCat = new DreamCat(breed, sex, deSexed, purebred, 0, 0, hairless);
                Pet pet = new Pet(name, microchip, age, dreamCat, adoptionFee);
                allPets.addPet(pet);
            }
            else {
                DreamDog dreamDog = new DreamDog(breed, sex, deSexed, purebred, 0, 0, training, exercise);
                Pet pet = new Pet(name, microchip, age, dreamDog, adoptionFee);
                allPets.addPet(pet);
            }
        }
        return allPets;
    }

    private static DreamPet getUserCriteria(String type) {

        String breed  = (String) JOptionPane.showInputDialog(null,"Please select your preferred breed."
                ,appName, JOptionPane.QUESTION_MESSAGE,null,allPets.getAllBreeds(type).toArray(), "");
        if(breed==null) System.exit(0);

        Sex sex = (Sex) JOptionPane.showInputDialog(null,"Please select your preferred sex:",
                appName, JOptionPane.QUESTION_MESSAGE,null,Sex.values(),Sex.FEMALE);
        if(sex==null) System.exit(0);

        DeSexed deSexed = (DeSexed) JOptionPane.showInputDialog(null,"Would you like your dog " +
                "to be de-sexed or not?",appName, JOptionPane.QUESTION_MESSAGE,null,DeSexed.values(),DeSexed.YES);
        if(deSexed==null) System.exit(0);

        Purebred purebred = (Purebred) JOptionPane.showInputDialog(null,"Would you like your " +
                "dog to be Purebred or not?",appName, JOptionPane.QUESTION_MESSAGE,null,Purebred.values(),Purebred.YES);
        if(purebred==null) System.exit(0);

        int minAge = -1, maxAge = -1;
        while(minAge==-1) {
            try {
                minAge = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the age " +
                        "(years) of the youngest dog you'd like to adopt ",appName,JOptionPane.QUESTION_MESSAGE));
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.");
            }
        }
        while(maxAge<minAge) {
            try {
                maxAge = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the age " +
                        "(years) of the oldest dog you'd be willing to adopt ",appName,JOptionPane.QUESTION_MESSAGE));
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.");
            }
            if(maxAge<minAge) JOptionPane.showMessageDialog(null,"Max age must be >= min age.");
        }

        DreamPet dreamPet;
        if (type.equalsIgnoreCase("cat")) {
            Hair hairless = (Hair) JOptionPane.showInputDialog(null,"Would you like a baldie " +
                    "or not?",appName, JOptionPane.QUESTION_MESSAGE,null,Hair.values(),Hair.HAIRLESS);
            if(hairless==null) System.exit(0);
            dreamPet = new DreamCat(breed, sex, deSexed, purebred,minAge, maxAge, hairless);
        } else {
            dreamPet = new DreamDog(breed, sex, deSexed, purebred,minAge, maxAge);
        }
        return dreamPet;
    }

    private static void processSearchResults(DreamPet userDreamPet) {
        List<Pet> allMatches = allPets.findMatch(userDreamPet);
        if(!allMatches.isEmpty()){
            Map<String,Pet> options = new HashMap<>();
            StringBuilder infoToShow = new StringBuilder("Matches found!! The following dogs meet your criteria: \n\n");
            for (Pet allMatch : allMatches) {
                infoToShow.append(allMatch.toString());
                options.put(allMatch.name() + " (" + allMatch.microchip() + ")", allMatch);
            }
            String adopt = (String) JOptionPane.showInputDialog(null,infoToShow+"\n\nPlease " +
                    "select which (if any) dog you'd like to adopt:","Pinkman's Pets Dog Finder",
                    JOptionPane.QUESTION_MESSAGE,null,options.keySet().toArray(), "");
            if(adopt==null) System.exit(0);
            else{
                Pet chosenPet = options.get(adopt);
                Person applicant = getUserDetails();
                writeAdoptionRequestToFile(applicant, chosenPet);
                JOptionPane.showMessageDialog(null, "Thank you! Your adoption request has been submitted. " +
                        "One of our friendly staff will be in touch shortly.", appName, JOptionPane.QUESTION_MESSAGE, null);
            }
        } else JOptionPane.showMessageDialog(null, "Unfortunately none of our pooches meet your criteria :(" +
                "\n\tTo exit, click OK.", appName, JOptionPane.QUESTION_MESSAGE, null);
        System.exit(0);
    }

    private static Person getUserDetails() {
        String name;
        do {
            name = JOptionPane.showInputDialog(null, "Please enter your full name.", appName, JOptionPane.QUESTION_MESSAGE);
            if(name==null) System.exit(0);
        } while(!isValidFullName(name));

        String phoneNumber;
        do{
            phoneNumber = JOptionPane.showInputDialog("Please enter your phone number (10-digit number in the format 0412345678): ");
            if(phoneNumber==null) System.exit(0);}
        while(!isValidPhoneNumber(phoneNumber));

        String email;
        do {
            email = JOptionPane.showInputDialog(null, "Please enter your email address.", appName, JOptionPane.QUESTION_MESSAGE);
            if (email == null) System.exit(0);
        }while(!isValidEmail(email));

        return new Person(name, phoneNumber, email);
    }

    private static void writeAdoptionRequestToFile(Person person, Pet pet) {
        String filePath = person.name().replace(" ","_")+"_adoption_request.txt";
        Path path = Path.of(filePath);
        String lineToWrite = person.name()+" wishes to adopt "+pet.name()+" ("+pet.microchip()+
                "). Their phone number is 0"+person.phoneNumber()+" and their email address is "+person.emailAddress();
        try {
            Files.writeString(path, lineToWrite);
        }catch (IOException io){
            System.out.println("File could not be written. \nError message: "+io.getMessage());
            System.exit(0);
        }
    }

    /**
     * a very simple regex for full name in Firstname Surname format
     * @param fullName the candidate full name entered by the user
     * @return true if name matches regex/false if not
     */
    public static boolean isValidFullName(String fullName) {
        String regex = "^[A-Z][a-z]+\\s[A-Z][a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    /**
     * a regex matcher that ensures that the user's entry starts with a 0 and is followed by 9 digits
     * @param phoneNumber the candidate phone number entered by the user
     * @return true if phone number matches regex/false if not
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * a regex matcher that ensures that the user's entry complies with RFC 5322
     * source: <a href="https://www.baeldung.com/java-email-validation-regex">...</a>
     * @param email the candidate email entered by the user
     * @return true if email matches regex/false if not
     */
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
