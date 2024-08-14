package Contests;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Map;

public class InformationWindow extends JFrame {
    private JTextArea infoArea;

    public InformationWindow(Scores scores) {
        setTitle("Competition Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        infoArea = new JTextArea();
        infoArea.setEditable(false);
        add(new JScrollPane(infoArea), BorderLayout.CENTER);

        // Update the information
        updateInformation(scores);

        // Periodically update information
        Timer timer = new Timer(500, e -> updateInformation(scores));
        timer.start();
    }

    private void updateInformation(Scores scores) {
        infoArea.setText("Scores:\n");
        Map<String, Date> allScores = scores.getAll();
        for (Map.Entry<String, Date> entry : allScores.entrySet()) {
            infoArea.append(entry.getKey() + " - " + entry.getValue() + "\n");
        }
    }
}
