import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataEntry {

    private static JTextField userName;
    private static JComboBox <String> breeds;
    private static JCheckBox desexed;
    private static String sex;

    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("We guiing bitch");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setIconImage(new ImageIcon("./icon.jpg").getImage());

        mainWindow.add(userSelections());
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> {
            System.out.println(userName.getText());
            System.out.println(breeds.getSelectedItem());
            System.out.println(desexed.isSelected());
            System.out.println(sex);
            writeToFile();
        });
        mainWindow.add(submit, BorderLayout.SOUTH);

        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    private static JPanel getUserName(){
        JLabel enterName = new JLabel("Enter your name");
        userName = new JTextField(12);
        JPanel namePanel = new JPanel();
        namePanel.add(enterName);
        namePanel.add(userName);
        namePanel.setBackground(Color.CYAN);
        return namePanel;
    }

    private static JPanel getUserBreed(){
        JLabel chooseBreed = new JLabel("Select or enter the breed youd like");

        String[] breedslist = {"poodle", "jack russell", "bulldog", "chihuahua", "rottweiler", "dalmation", "doberman", "pitbull"};
        breeds = new JComboBox<>(breedslist);
        JPanel breedPanel = new JPanel();
        breedPanel.add(chooseBreed);
        breedPanel.add(breeds);
        breedPanel.setBackground(Color.PINK);
        return breedPanel;
    }

    private static JPanel getdexsed(){
        JLabel desexedOption = new JLabel("Do you want desexed");
        desexed = new JCheckBox("Yes");
        JPanel desexedPanel = new JPanel();
        desexedPanel.add(desexedOption);
        desexedPanel.add(desexed);
        desexedPanel.setBackground(Color.GREEN);
        return desexedPanel;
    }

    private static JPanel getSex() {
        ButtonGroup genderButtonGroup = new ButtonGroup();
        JLabel whichSex = new JLabel("Do you want sex?");
        JRadioButton male = new JRadioButton(Sex.MALE.toString());
        JRadioButton female = new JRadioButton(Sex.FEMALE.toString());
        male.requestFocusInWindow();
        genderButtonGroup.add(male);
        genderButtonGroup.add(female);

        male.setActionCommand(Sex.MALE.toString());
        female.setActionCommand(Sex.FEMALE.toString());
        ActionListener actionListener = e -> sex = genderButtonGroup.getSelection().getActionCommand();
        male.addActionListener(actionListener);
        female.addActionListener(actionListener);

        JPanel sexPanel = new JPanel();
        sexPanel.add(whichSex);
        sexPanel.add(male);
        sexPanel.add(female);
        sexPanel.setBackground(Color.orange);
        return sexPanel;
    }

    public static JPanel userSelections(){
        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayout(4, 1));

        masterPanel.add(getUserName());
        masterPanel.add(getUserBreed());
        masterPanel.add(getdexsed());
        masterPanel.add(getSex());
        return masterPanel;
    }

    public static void writeToFile(){
        String dex = "";
        if (desexed.isSelected()) {
            dex = "desexed";
        }

        String message = userName.getText() + " wishes to adopt a " + dex + sex + breeds.getSelectedItem();
        Path path = Path.of("./orderFile");
        try {
            Files.writeString(path, message);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
