package Graphics;

import animals.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateCompetitionDialog extends JDialog {

    private JButton addGroupButton;
    private JButton animalAssociation;
    private CompetitionPanel competitionPanel;
    private List<String> groups;
    private JTable groupTable;
    private DefaultTableModel tableModel;
    private JButton startRegularCompetitionButton;
    private JButton startRelayCompetitionButton;


    public CreateCompetitionDialog(JFrame parent, CompetitionPanel competitionPanel) {
        super(parent, "Create New Competition", true);
        this.competitionPanel = competitionPanel;
        this.groups = new ArrayList<>();
        setLayout(new BorderLayout());

        // Radio buttons for competition type selection

        ButtonGroup competitionTypeGroup = new ButtonGroup();


        JPanel radioPanel = new JPanel();

        // Create a table to display the groups and animals
        String[] columnNames = {"Group Name", "Animals"};
        tableModel = new DefaultTableModel(columnNames, 0);
        groupTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(groupTable);

        // Buttons for adding groups and animals
        addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(e -> addNewGroup());

        animalAssociation = new JButton("Association Animal");
        animalAssociation.setEnabled(false); // Disabled until a group is added
        animalAssociation.addActionListener(e -> openAnimalAssociationDialog());
        startRegularCompetitionButton = new JButton("Start Regular Competition");
        startRegularCompetitionButton.setEnabled(false);
        startRegularCompetitionButton.addActionListener(e -> startRegularCompetition());

        startRelayCompetitionButton = new JButton("Start Relay Competition");
        startRelayCompetitionButton.setEnabled(false);
        startRelayCompetitionButton.addActionListener(e -> startRelayCompetition());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addGroupButton);
        buttonPanel.add(animalAssociation);
        buttonPanel.add(startRegularCompetitionButton);
        buttonPanel.add(startRelayCompetitionButton);

        add(radioPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // הוספת הטבלה למרכז החלון
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(700, 400);
        setLocationRelativeTo(parent);
    }

    private void addNewGroup() {
        String groupName = "Group " + (groups.size() + 1);
        groups.add(groupName);
        competitionPanel.addGroup(groupName); // Ensure the group is added to the CompetitionPanel

        tableModel.addRow(new Object[]{groupName, ""});
        animalAssociation.setEnabled(true);

        startRegularCompetitionButton.setEnabled(true);
        startRelayCompetitionButton.setEnabled(true);
    }

    private void openAnimalAssociationDialog() {
        if (groups.isEmpty()) return;

        // פותחים את החלון פעם אחת בלבד
        AnimalAssociationDialog dialog = new AnimalAssociationDialog(this, competitionPanel);
        dialog.setVisible(true);

        Animal selectedAnimal = dialog.getAnimal();
        String selectedGroup = dialog.getSelectedGroup();

        if (selectedAnimal != null && selectedGroup != null) {
            int rowIndex = findRowIndexByGroupName(selectedGroup);
            if (rowIndex != -1) {
                String existingAnimals = (String) tableModel.getValueAt(rowIndex, 1);
                tableModel.setValueAt(existingAnimals + (existingAnimals.isEmpty() ? "" : ", ") + selectedAnimal.getAnimaleName(), rowIndex, 1);
            }
        }
    }
    private int findRowIndexByGroupName(String groupName) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(groupName)) {
                return i;
            }
        }
        return -1;
    }
    private void startRegularCompetition() {
        // לוגיקה להפעלת תחרות רגילה
        JOptionPane.showMessageDialog(this, "Starting Regular Competition!");
        competitionPanel.startRegularCompetition();  // נניח שיש פונקציה כזו ב-CompetitionPanel
    }

    private void startRelayCompetition() {
        // לוגיקה להפעלת תחרות שליחים
        JOptionPane.showMessageDialog(this, "Starting Relay Competition!");
        competitionPanel.startRelayCompetition();  // נניח שיש פונקציה כזו ב-CompetitionPanel
    }
}
