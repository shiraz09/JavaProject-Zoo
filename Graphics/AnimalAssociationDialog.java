package Graphics;

import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class AnimalAssociationDialog extends JDialog {
    private JComboBox<String> animalComboBox;
    private JComboBox<String> groupComboBox;
    private JLabel categoryLabel;
    private Animal selectedAnimal;
    private String selectedGroup;
    private CompetitionPanel competitionPanel;

    // שימוש ב-Window כקלאס כללי במקום JFrame
    public AnimalAssociationDialog(Window parent, CompetitionPanel competitionPanel) {
        super(parent, "Add Animal", ModalityType.APPLICATION_MODAL);
        this.competitionPanel = competitionPanel;
        setLayout(new GridLayout(5, 2));

        JLabel animalLabel = new JLabel("Select Animal:");
        animalComboBox = new JComboBox<>();
        List<Animal> existingAnimals = competitionPanel.getAnimals();
        for (Animal animal : existingAnimals) {
            animalComboBox.addItem(animal.getAnimaleName());
        }
        categoryLabel = new JLabel("Category: ");

        animalComboBox.addActionListener(e -> updateCategoryLabel(existingAnimals));

        JLabel groupLabel = new JLabel("Select Group:");
        groupComboBox = new JComboBox<>();
        updateGroupComboBox();

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            selectedAnimal = existingAnimals.stream()
                    .filter(animal -> animal.getAnimaleName().equals(animalComboBox.getSelectedItem()))
                    .findFirst()
                    .orElse(null);
            selectedGroup = (String) groupComboBox.getSelectedItem();
            dispose();
        });

        add(animalLabel);
        add(animalComboBox);
        add(new JLabel("Animal Category:")); // תווית לטקסט קטגוריה
        add(categoryLabel);
        add(groupLabel);
        add(groupComboBox);
        add(new JLabel());  // רווח ריק
        add(okButton);

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }
    // מתודת עזר שמעדכנת את הקטגוריה של החיה הנבחרת
    private void updateCategoryLabel(List<Animal> existingAnimals) {
        String selectedAnimalName = (String) animalComboBox.getSelectedItem();
        if (selectedAnimalName != null) {
            Animal animal = existingAnimals.stream()
                    .filter(a -> a.getAnimaleName().equals(selectedAnimalName))
                    .findFirst()
                    .orElse(null);
            if (animal != null) {
                categoryLabel.setText(animal.getCategory()); // הצגת הקטגוריה של החיה בתווית
            }
        }
    }

    private void updateGroupComboBox() {
        groupComboBox.removeAllItems(); // Clear current items
        Map<String, List<Animal>> groups = competitionPanel.getGroupToAnimalsMap(); // Get the groups map

        for (String groupName : groups.keySet()) { // Iterate over the map and add items to the combo box
            groupComboBox.addItem(groupName);
        }
    }

    public Animal getAnimal() {
        return selectedAnimal;
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }
}