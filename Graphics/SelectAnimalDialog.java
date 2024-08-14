package Graphics;

import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SelectAnimalDialog extends JDialog {
    private JComboBox<String> groupComboBox;
    private JList<String> animalNameList;
    private CompetitionPanel competitionPanel;

    public SelectAnimalDialog(JFrame parent, CompetitionPanel competitionPanel, List<Animal> existingAnimals) {
        super(parent, "Select Animal", true);
        this.competitionPanel = competitionPanel;
        setLayout(new BorderLayout());

        // Label and ComboBox for selecting a group
        JLabel groupLabel = new JLabel("Select Group:");
        groupComboBox = new JComboBox<>();
        updateGroupComboBox();

        // List to display existing animal names
        JLabel animalLabel = new JLabel("Select Animal:");
        DefaultListModel<String> animalListModel = new DefaultListModel<>();
        for (Animal animal : existingAnimals) {
            animalListModel.addElement(animal.getAnimaleName()); // Add only animal names
        }
        animalNameList = new JList<>(animalListModel);
        animalNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane animalScrollPane = new JScrollPane(animalNameList);

        // OK button to confirm selection
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            String selectedAnimalName = animalNameList.getSelectedValue();
            String selectedGroup = (String) groupComboBox.getSelectedItem();

            if (selectedAnimalName != null && selectedGroup != null) {
                // Find the selected animal by name
                Animal selectedAnimal = existingAnimals.stream()
                        .filter(animal -> animal.getAnimaleName().equals(selectedAnimalName))
                        .findFirst()
                        .orElse(null);

                if (selectedAnimal != null) {
                    selectedAnimal.setGroup(selectedGroup); // נוודא ש selectedGroup הוא מסוג String
                    competitionPanel.addAnimalToGroup(selectedGroup, selectedAnimal);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select an animal and a group.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel layout
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(groupLabel);
        panel.add(groupComboBox);
        panel.add(animalLabel);
        panel.add(animalScrollPane);
        panel.add(new JLabel());
        panel.add(okButton);

        add(panel, BorderLayout.CENTER);

        setSize(300, 300);
        setLocationRelativeTo(parent);
    }

    private void updateGroupComboBox() {
        groupComboBox.removeAllItems();
        Map<Integer, String> groups = competitionPanel.getGroups();
        for (String groupName : groups.values()) {
            groupComboBox.addItem(groupName);
        }
    }
}