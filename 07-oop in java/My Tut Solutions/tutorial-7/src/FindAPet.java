

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
//import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindAPet {

    private final static String appName = "Pinkman's Pets Pet Finder";
    private final static String filePath = "./allPets2.txt";
    private final static String iconPath = "./icon.jpg";
    private static final ImageIcon icon = new ImageIcon(iconPath);
    private static AllPets allPets;

    /**
     * main method used to allow the user to search Pinkman's database of Pets, and place an adoption request
     * @param args none required
     */
    public static void main(String[] args) {
        allPets = loadPets();
        JOptionPane.showMessageDialog(null, "Welcome to Pinkman's Pets Pet Finder!\n\tTo start, click OK.", appName, JOptionPane.QUESTION_MESSAGE, icon);
        DreamPet petCriteria = getUserCriteria();
        processSearchResults(petCriteria);

        System.exit(0);
    }

    /**
     * method to load all Pet data from file, storing it as Pet objects in an instance of AllPets
     * @return an AllPets object - functions as database of Pets, with associated methods
     */
    private static AllPets loadPets() {
        AllPets allPets = new AllPets();
        Path path = Path.of(filePath);
        Map<Criteria, Object> criteraMap = new HashMap<>();

        List<String> PetData = null;
        try{
            PetData = Files.readAllLines(path);
        }catch (IOException io){
            System.out.println("Could not load the file. \nError message: "+io.getMessage());
            System.exit(0);
        }

        for (int i=1;i<PetData.size();i++) {
            String[] elements = PetData.get(i).split(",");

            String type = elements[0].toUpperCase();
            try {
                type = TypeOfPet.valueOf(type).toString();
            } catch (Exception e) {
                System.out.println("Guinea pig problems" + e);
            }

            String name = elements[1];
            long microchipNumber = 0;
            try{
                microchipNumber = Long.parseLong(elements[2]);
            }
            catch (NumberFormatException n){
                System.out.println("Error in file. Microchip number could not be parsed for Pet on line "+(i+1)+". Terminating. \nError message: "+n.getMessage());
                System.exit(0);
            }
            Sex sex = Sex.valueOf(elements[3].toUpperCase()); //add exception handling here
            DeSexed deSexed = DeSexed.valueOf(elements[4].toUpperCase()); //add exception handling here

            int age = 0;
            try{
                age = Integer.parseInt(elements[5]);
            }catch (NumberFormatException n){
                System.out.println("Error in file. Age could not be parsed for Pet on line "+(i+1)+". Terminating. \nError message: "+n.getMessage());
                System.exit(0);
            }

            String breed = elements[6].toLowerCase();
            Purebred purebred = Purebred.valueOf(elements[7].toUpperCase()); //add exception handling here

            double adoptionFee = 0;
            try{
                adoptionFee = Double.parseDouble(elements[8]);
            }catch (NumberFormatException n){
                System.out.println("Error in file. Adoption fee could not be parsed for Pet on line "+(i+1)+". Terminating. \nError message: "+n.getMessage());
                System.exit(0);
            }
            String Hair = elements[9].toUpperCase();
            String training = elements[10].toUpperCase();
//            int exercise = Integer.parseInt(elements[11]);
//            DreamPet dreamPet=null;
//            if(type.equals("cat")) {
//                Hair hair;
//                if(elements[9].equalsIgnoreCase("yes"))
//                    hair = Hair.HAIRLESS;
//                else hair=Hair.NA;
//                dreamPet = new DreamCat(breed,sex,deSexed,purebred,hair);
//            }
//            else if(type.equals("dog")){
//                LevelOfTraining levelOfTraining = LevelOfTraining.valueOf(elements[10]); //add exception handling here
//                int amountOfExercise = Integer.parseInt(elements[11]);
//                dreamPet = new DreamDog(breed, sex, deSexed, purebred, levelOfTraining, amountOfExercise);
//            }
            criteraMap.put(Criteria.TYPE, type);
            criteraMap.put(Criteria.BREED, breed);
            criteraMap.put(Criteria.SEX, sex);
            criteraMap.put(Criteria.DESEXED, deSexed);
            criteraMap.put(Criteria.PUREBREED, purebred);
            criteraMap.put(Criteria.HAIR, Hair);

            DreamPet dreamPet = new DreamPet(criteraMap);
            Pet Pet = new Pet(name, microchipNumber,age, adoptionFee, dreamPet);

            allPets.addPet(Pet);
            for (Criteria key :criteraMap.keySet()) {
                System.out.println(key.toString() + criteraMap.values());
            }
        }
        return allPets;
    }

    /**
     * generates JOptionPanes requesting user input for Pet breed, sex, de-sexed status and age
     * @return a DreamPet object representing the user's desired Pet criteria
     */
    private static DreamPet getUserCriteria(){
        Map<Criteria, Object> usersDreamPet = new LinkedHashMap<>();

        TypeOfPet type = (TypeOfPet) JOptionPane.showInputDialog(null,"Please select the type of pet you'd like to adopt.",appName, JOptionPane.QUESTION_MESSAGE,icon,TypeOfPet.values(), TypeOfPet.DOG);
        if(type==null) System.exit(0);
        usersDreamPet.put(Criteria.TYPE, type);

        String breed  = (String) JOptionPane.showInputDialog(null,"Please select your preferred" +
                " breed.",appName, JOptionPane.QUESTION_MESSAGE,icon,allPets.getAllBreeds(type.toString()).toArray(), "");
        if(breed==null) System.exit(0);
        else if (!breed.equalsIgnoreCase("na")) {
            usersDreamPet.put(Criteria.BREED, breed);
        }

        Sex sex = (Sex) JOptionPane.showInputDialog(null,"Please select your preferred sex:",appName, JOptionPane.QUESTION_MESSAGE,icon,Sex.values(),Sex.FEMALE);
        if(sex==null) System.exit(0);
        usersDreamPet.put(Criteria.SEX, sex);

        DeSexed deSexed = (DeSexed) JOptionPane.showInputDialog(null,"Would you like your Pet to be de-sexed or not?",appName, JOptionPane.QUESTION_MESSAGE,icon,DeSexed.values(),DeSexed.YES);
        if(deSexed==null) System.exit(0);
        usersDreamPet.put(Criteria.DESEXED, deSexed);

        Purebred purebred  = (Purebred) JOptionPane.showInputDialog(null,"Would you like the Pet to be a purebred?",appName, JOptionPane.QUESTION_MESSAGE,null,Purebred.values(), "");
        if(purebred==null) System.exit(0);
        else if (!(purebred==Purebred.NA)) {
            usersDreamPet.put(Criteria.PUREBREED, purebred);
        }

//        int minAge = -1, maxAge = -1;
//        while(minAge==-1) {
//            try {
//                minAge = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the age (years) of the youngest Pet you'd like to adopt ",appName,JOptionPane.QUESTION_MESSAGE));
//            }
//            catch (NumberFormatException e){
//                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.");
//            }
//        }
//        while(maxAge<minAge) {
//            try {
//                maxAge = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the age (years) of the oldest Pet you'd be willing to adopt ",appName,JOptionPane.QUESTION_MESSAGE));
//            }
//            catch (NumberFormatException e){
//                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.");
//            }
//            if(maxAge<minAge) JOptionPane.showMessageDialog(null,"Max age must be >= min age.");
//        }


        if(type.toString().equalsIgnoreCase("Cat")) {
            Hair hair  = (Hair) JOptionPane.showInputDialog(null,"Please select from the following options","Pinkman's Pet Finder", JOptionPane.QUESTION_MESSAGE,null,Hair.values(), "");
            if(hair==null) System.exit(0);
            else if (!(hair==Hair.NA)) {
                usersDreamPet.put(Criteria.HAIR, hair);
            }
        }

        int[] age = minMaxValues("min age ", "max age ");
//        int[] price = minMaxValues("min price ", "max price");

        return new DreamPet(usersDreamPet, age[0], age[1]);
    }

    private static int[] minMaxValues(String lowest, String highest) {

        int minAge = -1, maxAge = -1;
        while(minAge==-1) {
            try {
                minAge = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the " + lowest ,appName,JOptionPane.QUESTION_MESSAGE));
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.");
            }
        }

        while(maxAge<minAge) {
            try {
                maxAge = Integer.parseInt(JOptionPane.showInputDialog(null,"What is the " + highest ,appName,JOptionPane.QUESTION_MESSAGE));
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Invalid input. Please try again.");
            }
            if(maxAge<minAge) JOptionPane.showMessageDialog(null,"Max age must be >= min age.");
        }
        int[] values = new int[]{minAge,maxAge};
        return values;
    }

    /**
     * method to display  results (if there are any) to the user in the form of a drop-down list
     * allowing them to select and adopt a pet of their choice.
     * @param dreamPet a DreamPet object representing the user's selections
     */
    private static void processSearchResults(DreamPet dreamPet){
        List<Pet> potentialMatches = allPets.findMatch(dreamPet);
        if(potentialMatches.size()>0){
            Map<String,Pet> options = new HashMap<>();
            StringBuilder infoToShow = new StringBuilder("Matches found!! The following Pets meet your criteria: \n");
            for (Pet potentialMatch : potentialMatches) {
                // TODO - I might have to pass the map into the to string here? not sure how?
                infoToShow.append("\n").append(potentialMatch.toString());
                options.put(potentialMatch.name() + " (" + potentialMatch.microchipNumber() + ")", potentialMatch);
            }
            String adopt = (String) JOptionPane.showInputDialog(null,infoToShow+"\nPlease select which " +
                    "Pet you'd like to adopt:",appName, JOptionPane.QUESTION_MESSAGE,icon,options.keySet().toArray(), "");
            if(adopt==null) System.exit(0);
            else{
                Pet chosenPet = options.get(adopt);
                Person applicant = getUserDetails();
                writeAdoptionRequestToFile(applicant, chosenPet);
                JOptionPane.showMessageDialog(null, "Thank you! Your adoption request has been submitted. \n" +
                        "One of our friendly staff will be in touch shortly.", appName, JOptionPane.QUESTION_MESSAGE, icon);
            }
        } else JOptionPane.showMessageDialog(null, "Unfortunately none of our pooches meet your criteria :(" +
                "\n\tTo exit, click OK.", appName, JOptionPane.QUESTION_MESSAGE, icon);
    }

    /**
     * method to get user to input name, ph num and email, with appropriate input validation
     * @return a Person object representing the user of the program
     */
    private static Person getUserDetails(){
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

    /**
     * provides Pinkman's Pets with a file containing the user's adoption request
     * @param person a Person object representing the user
     * @param Pet a Pet object representing the Pet that the user wants to adopt
     */
    private static void writeAdoptionRequestToFile(Person person, Pet Pet) {
        String filePath = person.name().replace(" ","_")+"_adoption_request.txt";
        Path path = Path.of(filePath);
        String lineToWrite = person.name()+" wishes to adopt "+Pet.name()+" ("+Pet.microchipNumber()+
                "). Their phone number is "+person.phoneNumber()+" and their email address is "+person.emailAddress();
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
