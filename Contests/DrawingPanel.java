package Contests;

import animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Animal> animals;

    public DrawingPanel(List<Animal> animals) {
        this.animals = animals;
        new Thread(this::drawAnimals).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Animal animal : animals) {
            synchronized (animal) {
                // Draw animal based on its position
                g.fillOval((int) animal.getPosition().getX(), (int) animal.getPosition().getY(), 10, 10);
            }
        }
    }

    private void drawAnimals() {
        while (!Thread.currentThread().isInterrupted()) {
            repaint();
            try {
                Thread.sleep(100); // Update interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
