import javax.swing.JOptionPane;

/**
 * Program to outoput the life expectancy of hound dogs.
 */

public class HoundLifeExpectancy {

    /**
     * Asks user to input the breeed of a hound dog, then uses a switch statement to match the user input
     * to a documented dog breed and returns via a Joptionpane, the life expectancy of that dog breed
     * unless the breed isnt in our catalogue, then returns "Dog not in the system" to the user before closing
     * @param args none required
     */

    public static void main(String args[]){
        String houndBreed = JOptionPane.showInputDialog("Enter the name of a hound breed");

        // Should have converted user input into all lower case with houndDog.toLowerCase();
        // to control user input

        String outputString;
        switch(houndBreed){
            case "Afghan Hound" -> outputString = "The " + houndBreed + " has a life expectancy of 12-18 years";
            case "American English Coonhound", "Bluetick Coonhound" -> outputString = "The " + houndBreed + " has a life expectancy of 11-12 years";
            case "American Foxhound"  -> outputString = "The " + houndBreed + " has a life expectancy of 11-13 years";
            case "Azawakh", "Harrier", "Norwegian Elkhound", "Portuguese Podengo Pequeno", "Redbone Coonhound", "Whippet" -> outputString = "The " + houndBreed + " has a life expectancy of 12-15 years";
            // Default incase of typos or dog not cataloged
            default -> outputString = "Dog not in system";
        }
        JOptionPane.showMessageDialog(null, outputString);
        // To free up memory? I think, something to do with that... havent been taught this yet
        System.exit(0);
    }
}
