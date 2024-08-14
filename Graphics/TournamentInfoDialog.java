package Graphics;

import animals.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TournamentInfoDialog extends JDialog {
    private JTable infoTable;
    private DefaultTableModel tableModel;

    public TournamentInfoDialog(JFrame parent, CompetitionPanel competitionPanel) {
        super(parent, "Tournament Info", true);
        setLayout(new BorderLayout());

        String[] columnNames = {"Group", "Animal", "Current Position"};
        tableModel = new DefaultTableModel(columnNames, 0);
        infoTable = new JTable(tableModel);

        updateTableData(competitionPanel.getGroupToAnimalsMap());

        add(new JScrollPane(infoTable), BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    private void updateTableData(Map<String, List<Animal>> groupToAnimalsMap) {
        tableModel.setRowCount(0); // Clear existing data
        for (String group : groupToAnimalsMap.keySet()) {
            List<Animal> animals = groupToAnimalsMap.get(group);
            for (Animal animal : animals) {
                tableModel.addRow(new Object[]{group, animal.getAnimaleName(), animal.getPosition()});
            }
        }
    }
}
