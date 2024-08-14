/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package Graphics;
import animals.Animal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;


public class AddAnimalDialog extends JDialog {
    private JComboBox<String> typeComboBox;
    private JComboBox<String> competitionTypeComboBox;
    private JButton addButton;
    private Animal animal;
    private String selectedGroup;
    private JComboBox<String> groupComboBox;
    private CompetitionPanel competitionPanel;
    private JList<Animal> animalList;
    public AddAnimalDialog(JFrame parent,CompetitionPanel competitionPanel) {
        super(parent, "Add Animal", true);
        this.competitionPanel = competitionPanel;
        setLayout(new GridLayout(5, 2));
        JLabel animalTypeLabel = new JLabel("Animal Type:");
        JComboBox<String> animalTypeComboBox = new JComboBox<>(new String[]{"Alligator", "Cat", "Dog", "Dolphin", "Eagle", "Pigeon", "Snake", "Whale"});
        JLabel competitionTypeLabel = new JLabel("Competition Type:");
        JComboBox<String> competitionTypeComboBox = new JComboBox<>(new String[]{"Race", "Swimming", "Jumping"});
        JLabel trackLabel = new JLabel("Track:");
        JTextField trackField = new JTextField();
        JLabel groupLabel = new JLabel("Select Group:");
        groupComboBox = new JComboBox<>();
        updateGroupComboBox();

        JButton okButton = new JButton("OK");


        add(animalTypeLabel);
        add(animalTypeComboBox);
        add(competitionTypeLabel);
        add(competitionTypeComboBox);
        add(trackLabel);
        add(trackField);
        add(new JLabel());
        add(okButton);

        okButton.addActionListener(e -> {
            String animalType = (String) animalTypeComboBox.getSelectedItem();
            String competitionType = (String) competitionTypeComboBox.getSelectedItem();
            String track = trackField.getText().trim();
            String selectedGroup = (String) groupComboBox.getSelectedItem();
            try {
                if (animalType == null || competitionType == null) {
                    throw new IllegalArgumentException("All fields must be filled out.");
                }
                validateCompetitionType(animalType, competitionType);
                int trackNumber = Integer.parseInt(track); /** Change to numeric format **/
                AnimalSettingsDialog settingsDialog = new AnimalSettingsDialog(parent, animalType, 0, 0, competitionType);
                settingsDialog.setVisible(true);
                animal = settingsDialog.getAnimal();
                animal.setInitialPosition(competitionType, trackNumber); /** Setting the starting position **/
                animal.setGroup(selectedGroup);
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setSize(300, 200);
        setLocationRelativeTo(parent);
    }
    private void updateGroupComboBox() {
        groupComboBox.removeAllItems();
        Map<Integer, String> groups = competitionPanel.getGroups();
        for (String groupName : groups.values()) {
            groupComboBox.addItem(groupName);
        }
    }


    private void validateCompetitionType(String animalType, String competitionType) {
        if ((competitionType.equals("Race") && !isLandAnimal(animalType)) ||
                (competitionType.equals("Jumping") && !isAirAnimal(animalType)) ||
                (competitionType.equals("Swimming") && !isWaterAnimal(animalType))) {
            throw new IllegalArgumentException("The animal type does not match the competition type.");
        }
    }

    private boolean isLandAnimal(String animalType) {
        return animalType.equals("Cat") || animalType.equals("Dog") || animalType.equals("Snake")|| animalType.equals("Alligator");
    }

    private boolean isAirAnimal(String animalType) {
        return animalType.equals("Eagle") || animalType.equals("Pigeon");
    }

    private boolean isWaterAnimal(String animalType) {
        return animalType.equals("Alligator") || animalType.equals("Dolphin") || animalType.equals("Whale");
    }

    private int determineInitialX(String competitionType, String location) {
        if (competitionType.equals("Race")) {
            return 0;
        } else if (competitionType.equals("Jumping")) {
            int track = Integer.parseInt(location);
            if (track < 1 || track > 5) {
                throw new IllegalArgumentException("Air track must be between 1 and 5.");
            }
            return track;
        } else { // Water
            int track = Integer.parseInt(location);
            if (track < 1 || track > 4) {
                throw new IllegalArgumentException("Water track must be between 1 and 4.");
            }
            return track;
        }
    }

    public Animal getAnimal() {
        return animal;
    }
    public String getSelectedGroup() {
        return selectedGroup;
    }
}