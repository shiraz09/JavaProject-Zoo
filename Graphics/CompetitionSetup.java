package Graphics;

import animals.Animal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Map;
import java.util.List; // Correct import for generic List


public class CompetitionSetup extends JFrame {
    private JRadioButton regularCompetitionButton;
    private JRadioButton relayRaceButton;
    private JButton addAnimalButton;
    private JButton addGroupButton;
    private JTable animalTable;
    private CompetitionPanel competitionPanel;
    private DefaultTableModel model;
    private Map<Integer, String> groups;

    public CompetitionSetup(CompetitionPanel competitionPanel) {
        this.competitionPanel = competitionPanel;
        setTitle("Competition Setup");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        regularCompetitionButton = new JRadioButton("Regular Competition");
        relayRaceButton = new JRadioButton("Relay Race");
        group.add(regularCompetitionButton);
        group.add(relayRaceButton);
        topPanel.add(regularCompetitionButton);
        topPanel.add(relayRaceButton);

        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Group 1"}, 0);
        animalTable = new JTable(model);
        add(new JScrollPane(animalTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        this.groups = competitionPanel.getGroups();

        // Initialize the addAnimalButton
        addAnimalButton = new JButton("Add Animal");
        addAnimalButton.addActionListener(e -> {
            try {
                List<Animal> existingAnimals = competitionPanel.getAnimals();
                if (existingAnimals.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No animals available to add.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                SelectAnimalDialog selectAnimalDialog = new SelectAnimalDialog(this, competitionPanel, existingAnimals);
                selectAnimalDialog.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Initialize the addGroupButton
        addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(e -> {
            model = (DefaultTableModel) animalTable.getModel();
            int columnCount = model.getColumnCount();
            String newGroupName = "Group " + (columnCount + 1);

            boolean groupExists = false;
            for (int i = 0; i < columnCount; i++) {
                if (model.getColumnName(i).equals(newGroupName)) {
                    groupExists = true;
                    break;
                }
            }

            if (!groupExists) {
                model.addColumn(newGroupName);
                competitionPanel.addGroup(newGroupName);

                for (int i = 0; i < model.getRowCount(); i++) {
                    model.setValueAt("", i, columnCount);
                }
            }
        });

        bottomPanel.add(addAnimalButton);
        bottomPanel.add(addGroupButton);
        add(bottomPanel, BorderLayout.SOUTH);
        loadTableData();
    }

    // This method handles adding an animal to the correct group in the table
    public void addAnimalToGroup(Animal animal, String groupName) {
        int groupCol = findGroupColumn(groupName);
        if (groupCol == -1) {
            return; // Group not found, handle as needed
        }

        DefaultTableModel model = (DefaultTableModel) animalTable.getModel();
        boolean animalAdded = false;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, groupCol) == null || model.getValueAt(i, groupCol).equals("")) {
                model.setValueAt(animal.getAnimaleName(), i, groupCol);
                animalAdded = true;
                break;
            }
        }

        if (!animalAdded) {
            Object[] row = new Object[model.getColumnCount()];
            Arrays.fill(row, "");
            row[groupCol] = animal.getAnimaleName();
            model.addRow(row);
        }

        animalTable.revalidate();
        animalTable.repaint();
    }

    private int findGroupColumn(String groupName) {
        for (int i = 0; i < animalTable.getColumnCount(); i++) {
            if (animalTable.getColumnName(i).equals(groupName)) {
                return i;
            }
        }
        return -1; // Return -1 if the group is not found
    }
    public Map<Integer, String> getGroups() {
        return this.groups;
    }
    private void loadTableData() {
        Map<String, List<Animal>> groupToAnimalsMap = competitionPanel.getGroupToAnimalsMap();

        for (String groupName : groupToAnimalsMap.keySet()) {
            int groupCol = findGroupColumn(groupName);
            if (groupCol == -1) {
                model.addColumn(groupName);
                groupCol = model.getColumnCount() - 1;
            }

            List<Animal> animals = groupToAnimalsMap.get(groupName);
            for (Animal animal : animals) {
                boolean added = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, groupCol) == null || model.getValueAt(i, groupCol).equals("")) {
                        model.setValueAt(animal.getAnimaleName(), i, groupCol);
                        added = true;
                        break;
                    }
                }

                if (!added) {
                    Object[] row = new Object[model.getColumnCount()];
                    row[groupCol] = animal.getAnimaleName();
                    model.addRow(row);
                }
            }
        }
    }
    private CompetitionSetup competitionSetup;
    public void openCompetitionSetup() {
        if (competitionSetup == null) {
            competitionSetup = new CompetitionSetup(competitionPanel);
        }
        competitionSetup.setVisible(true);
    }

}