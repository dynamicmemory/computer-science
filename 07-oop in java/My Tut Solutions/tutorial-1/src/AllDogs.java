import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Reads dog information from a file as well as writes new dogs to that file
 */

public class AllDogs {

    /**
     * Opens a given file that contains basic information about a group of dogs
     * prints the content of the file to the console. Then prompts the user to
     * fill in a sort of dog questionaire. Then finally takes all the anwers and
     * updates the file with those answerss.
     * @param args none required
     * @throws IOException
     */

    public static void main(String args[]) throws IOException {
        // Specify file path for the file to read and write too
        String filePath = "./dogs.txt";
        String fileContents = "";

        try {
            fileContents = Files.readString(Path.of(filePath));
        }
        catch(IOException e) {
            System.out.println("File does now exist \n Error message reads: " + e);
        }
        finally {
            System.out.println("Check file path or spelling and try again.");
        }
        // Prints out the contents of the file if possible
        System.out.println("**FILE CONTENTS**\n"+fileContents+"**END OF FILE**\n");

        // Essentially create an object? to refer to for input?... I think
        Scanner keys = new Scanner(System.in);

        // The pattern below is; 1) Print a statement asking for input, 2) Declare a variable to assign the input.
        System.out.println("Please enter the dog's name:");
        String name = keys.nextLine();

        System.out.println("Please enter the dog's microchip number:");
        long microNum = keys.nextLong();

        System.out.println("Please enter the dog's gender: (female/male/unknown)");
        String gender = keys.nextLine();

        System.out.println("Is the dog desexed? (true/false)");
        boolean desexed = keys.nextBoolean();
        String desexedToPrint;
        if(desexed) desexedToPrint = "yes";
        else desexedToPrint = "no";

        System.out.println("Please enter the dogs age");
        int age = keys.nextInt();

        System.out.println("Please enter the dogs breed");
        String breed = keys.nextLine();

        System.out.println("Is the dog purebred? (true/false)");
        boolean purebred = keys.nextBoolean();
        String purebredToPrint;
        if(purebred) purebredToPrint = "yes";
        else purebredToPrint = "no";

        System.out.println("Please enter the dogs height:");
        double height = keys.nextDouble();

        System.out.println("Please enter the dogs weight:");
        double weight = keys.nextDouble();

        // Concatinate all the newly gathered information to prepare it to be added to the file
        String newDog = name+","+microNum+","+gender+","+desexed+","+age+","+breed+","+purebred+","+height+","+weight+"\n";

        String toWrite = fileContents+newDog;
        // Write everything to the file with all the updated information
        Files.writeString(Path.of(filePath), toWrite);
        System.out.println("**ALTERED FILE CONTENTS**\n"+toWrite+"**END OF FILE**");
    }
}
