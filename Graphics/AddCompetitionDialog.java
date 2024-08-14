/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package Graphics;
import animals.Animal;
import mobility.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddCompetitionDialog extends JDialog {
    private Competition competition;
    private JTextField locationField;
    private JComboBox<String> competitionTypeComboBox;
    private ArrayList<String> competitionsList;
    public AddCompetitionDialog(JFrame parent,ArrayList<String> competitionsList) {
        super(parent, "Add Competition", true);
        this.competitionsList = competitionsList;
        setLayout(new GridLayout(3, 2));

        JLabel competitionTypeLabel = new JLabel("Competition Type:");
        JComboBox<String> competitionTypeComboBox = new JComboBox<>(new String[]{"Race", "Swimming", "Jumping"});
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String selectedType = (String) competitionTypeComboBox.getSelectedItem();
            competitionsList.add(selectedType);
            dispose();
            dispose();
        });

        add(competitionTypeLabel);
        add(competitionTypeComboBox);
        add(new JLabel()); /** Filler **/
        add(addButton);
        setSize(300, 200);
        setLocationRelativeTo(parent);
    }

    public Competition getCompetition() {
        return competition;
    }
}