import javax.swing.*;

/**
 * Apparently we are requesting to adopt dogs im guessing
 */

public class AdoptionRequest {
    /***
     *
     * @param args none required
     */
    public static void main(String[] args) {
        // Intro message displayed
        JOptionPane.showMessageDialog(null,"Welcome to Mokoto's dog adoption service. " +
                "Press ok to begin our adoption questionaire.");

        // Initialize an int to check against
        int check = 0;
        boolean exit = false;

//         Dog breed question
        while (check == 0 && !exit) {
            String dogBreed = JOptionPane.showInputDialog("Which dog breed all you looking to adopt");
            // conditional to handle null pointer error
            if (dogBreed == null){
                JOptionPane.showMessageDialog(null, "Thank you have a nice day.");
                exit = true;
            }
            else {
                check = 1;
            }
        }
//
        // Pure bred question
        while (check == 1 && !exit) {
            String pureBred = JOptionPane.showInputDialog("Would you like to adopt a purebred? (yes/no)");
            if (pureBred == null) {
                JOptionPane.showMessageDialog(null, "Thank you have a nice day.");
                exit = true;
            }
            else if (pureBred.equalsIgnoreCase("yes") || pureBred.equalsIgnoreCase("no")) {
                check = 0;
            }
        }

        // lil do while prac for some sex

        while (check == 0 && !exit) {
            String sex = JOptionPane.showInputDialog("Which sex would you like (male, female or either)");

            if (sex == null) {
                JOptionPane.showMessageDialog(null, "Thank you have a nice day.");
                exit = true;
            }
            else if (sex.equalsIgnoreCase("male") || sex.equalsIgnoreCase("female") || sex.equalsIgnoreCase("either")) {
                check = 1;
            }
        }


        // Get max age (im not convinced ive handle the possible problems with this correctly, feels bloated af)
        while (check == 1 && !exit) {
            try {
                String maxAge = JOptionPane.showInputDialog("How old of a pup are you willing to adopt?");
                if (maxAge == null) {
                    JOptionPane.showMessageDialog(null, "Thank you have a nice day.");
                    exit = true;
                }
                else if (Integer.parseInt(maxAge) > -1 && Integer.parseInt(maxAge) < 21) {
                    check = 0;
                }
                else {
                    JOptionPane.showMessageDialog(null,"Please enter an age between 0 - 20");
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Please enter an age between 0 - 20");
            }
        }

        // Get the min age, same as above, feels terrible to write this like this, im missing the point for sure
        while (check == 0 && !exit) {
            try {
                String minAge = JOptionPane.showInputDialog("How old of a pup are you willing to adopt?");
                if (minAge == null) {
                    JOptionPane.showMessageDialog(null, "Thank you have a nice day.");
                    exit = true;
                }
                else if (Integer.parseInt(minAge) > -1 && Integer.parseInt(minAge) < 21) {
                    check = 1;
                }
                else {
                    JOptionPane.showMessageDialog(null,"Please enter an age between 0 - 20");
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Please enter an age between 0 - 20");
            }
        }

        // Gets the customers name
        while (check == 1 && !exit) {
            String name = JOptionPane.showInputDialog("Please enter your name:");
            if (name == null) {
                JOptionPane.showMessageDialog(null, "Thank you have a nice day.");
                exit = true;
            }
            check = 0;
        }

        // Gets the phone number
        while (check == 0 && !exit) {
            try {
                String number = JOptionPane.showInputDialog("Please enter your phone number");
                if (number == null) {
                    JOptionPane.showMessageDialog(null, "Thank you have a nice day.");
                    exit = true;
                }
                else if (number.length() == 10) {
                    check = 1;
                    JOptionPane.showMessageDialog(null, "Thank you we will process your request shortly");
                }
                else {
                    JOptionPane.showMessageDialog(null,"Input didnt match a 10 digit number, please try again");
                }
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Input didnt match a 10 digit number, please try again");
            }
        }
    }
}
