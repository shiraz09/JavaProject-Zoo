/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompetitionFrame extends JFrame {
    private ArrayList<String> competitionsList;

    public static void main(String[] args) {
        // Start the application by showing the main CompetitionFrame
        SwingUtilities.invokeLater(() -> {
            CompetitionFrame frame = new CompetitionFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800); // Set appropriate size
            frame.setVisible(true);
        });
    }

    public CompetitionFrame() {
        super("Animal Competition");
        competitionsList = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        CompetitionPanel competitionPanel = new CompetitionPanel();
        add(competitionPanel, BorderLayout.CENTER);;

        // Create a menu bar with items to add a new competition and exit
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem addCompetitionItem = new JMenuItem("Add Competition");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem helpItem = new JMenuItem("Help");

        // Add action listeners
        addCompetitionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAnimalDialog addAnimalDialog = new AddAnimalDialog(CompetitionFrame.this, competitionPanel);
                addAnimalDialog.setVisible(true);
            }
        });

        exitItem.addActionListener(e -> System.exit(0));
        helpItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Home Work 2\n GUI"));

        menu.add(addCompetitionItem);
        menu.add(exitItem);
        helpMenu.add(helpItem);
        menuBar.add(menu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        setVisible(true); // Make sure the frame is visible
    }
}