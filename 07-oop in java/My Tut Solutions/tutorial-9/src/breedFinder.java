import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class breedFinder {

    public static void main (String[] args) {
        JFrame mainWindow = new JFrame("Dog Breed Finder");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(500,500));
        mainWindow.setIconImage(new ImageIcon("./icon.jpg").getImage() );
        mainWindow.add(getUserInput(), BorderLayout.CENTER);

        JLabel dogImage = new JLabel(new ImageIcon("./dog.png"));
        mainWindow.add(dogImage, BorderLayout.EAST);
        mainWindow.setVisible(true);
        mainWindow.pack();
    }

    private static Map<String, Integer> loadPets(){
        Path path = Path.of("./allPets.txt");
        Map<String, Integer> dogBreeds = new HashMap<>();
        List<String> petsList;
        try {
            petsList = Files.readAllLines(path);
            petsList.removeFirst();

            for (String line : petsList){
                String[] petInfo = line.split(",");
                String animal = petInfo[0];
                String breed = petInfo[6].toLowerCase();

                if (animal.equalsIgnoreCase("dog")){
                    if (dogBreeds.containsKey(breed)) {
                        dogBreeds.put(breed, dogBreeds.get(breed) + 1);
                    }
                    else {
                        dogBreeds.put(breed, 1);
                    }
                }
            }
        } catch(IOException e) {
            System.out.println("You fucked up dawg" + e);
        }

        return dogBreeds;
    }

    private static JPanel getUserInput() {

//        JLabel userBreedLabel = new JLabel("Please enter your prefered dog breed");
//        JTextField userBreedField = new JTextField(22);
        String[] mappedBreeds = loadPets().keySet().toArray(new String[0]);
//        JComboBox<String> allBreeds = new JComboBox<>(mappedBreeds);
        JList<String> allBreeds = new JList<>(mappedBreeds);

        JLabel userFeedback = new JLabel("Select a dog to find out if they are available, select multiple if you'd like");

        ListSelectionListener listener = e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("We have ");
            for (String dog : allBreeds.getSelectedValuesList()) {
                int number = loadPets().get(dog);
                sb.append(number + " " + dog + "s, ");
            }
            sb.append("available for adoption");
            userFeedback.setForeground(Color.BLUE);
            userFeedback.setText(sb.toString());
        };

        allBreeds.addListSelectionListener(listener);

//        JButton submitQuery = new JButton("Check Availability");

//        ActionListener listener = e -> {

//            if (userBreedField.getText().isEmpty()) {
//                userFeedback.setForeground(Color.RED);
//                userFeedback.setText("Textfeild cannot be empty, please enter the breed you would like");
//            }
//            else if (loadPets().containsKey(userBreedField.getText())) {
//                int num_of_pets = loadPets().get(userBreedField.getText());
//                userFeedback.setForeground(Color.BLUE);
//                userFeedback.setText("We have " + num_of_pets +" "+ userBreedField.getText());
//            }
//            else {
//                userFeedback.setForeground(Color.RED);
//                userFeedback.setText("Sorry we have no " + userBreedField.getText());

//            String breedName = (String) allBreeds.getSelectedItem();
//            int num_of_pets = loadPets().get(breedName);
//            userFeedback.setForeground(Color.BLUE);
//            userFeedback.setText("We have " + num_of_pets + " " + breedName);
//            playSound(num_of_pets);
//        };

//        allBreeds.addActionListener(listener);
//        userBreedField.addActionListener(listener);
//        submitQuery.addActionListener(listener);


        JPanel userPanel = new JPanel();
//        userPanel.setLayout(new FlowLayout());
        userPanel.setBorder(BorderFactory.createTitledBorder("Please enter your prefered dog breed"));
//        userPanel.add(userBreedLabel);
        userPanel.add(allBreeds);
//        userPanel.add(userBreedField);
//        userPanel.add(submitQuery);
        userPanel.add(userFeedback);


        return userPanel;
    }

    private static void playSound(int amount){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("./bark.wav")));
            clip.loop(amount - 1);

        }
        catch (Exception e) {

        }
    }
}
