
package Graphics;
import javax.swing.*;
import java.awt.*;
import Olympics.Medal;
import animals.*;

public class AnimalSettingsDialog extends JDialog {
    private final String animalType;
    private final String competitionType;
    private final int initialX;
    private final int initialY;
    private Animal animal;

    public AnimalSettingsDialog(JFrame parent, String animalType, int initialX, int initialY, String competitionType) {
        super(parent, "Animal Settings: " + animalType, true);
        this.animalType = animalType;
        this.initialX = initialX;
        this.initialY = initialY;
        this.competitionType = competitionType;
        setLayout(new GridLayout(10, 2));

        JLabel NameLabel = new JLabel("Name:");
        JTextField NameField = new JTextField();
        JLabel speedLabel = new JLabel("Speed:");
        JTextField speedField = new JTextField();
        JLabel energyAmountLabel = new JLabel("Energy Amount:");
        JTextField energyAmountField = new JTextField();
        JLabel energyConsumptionLabel = new JLabel("Energy Consumption:");
        JTextField energyConsumptionField = new JTextField();
        JLabel distanceLabel = new JLabel("Distance:");
        JTextField distanceField = new JTextField();
        JButton createButton = new JButton("Create");

        add(NameLabel);
        add(NameField);
        add(speedLabel);
        add(speedField);
        add(energyAmountLabel);
        add(energyAmountField);
        add(energyConsumptionLabel);
        add(energyConsumptionField);
        add(distanceLabel);
        add(distanceField);
        add(new JLabel());
        add(createButton);

        createButton.addActionListener(e -> {
            try {
                String name = NameField.getText();
                double speed = Double.parseDouble(speedField.getText());
                double energyAmount = Double.parseDouble(energyAmountField.getText());
                double energyConsumption = Double.parseDouble(energyConsumptionField.getText());
                double distance = Double.parseDouble(distanceField.getText());

                if (name.isEmpty() || speed <= 0 || energyAmount <= 0 || energyConsumption <= 0 || distance <= 0) {
                    throw new IllegalArgumentException("All fields must be filled out correctly.");
                }

                /** Check if competition type matches animal type **/
                if (!isCompetitionTypeValidForAnimal(animalType, competitionType)) {
                    throw new IllegalArgumentException("Competition type does not match the animal type.");
                }
                animal = createAnimal(animalType, name, speed, energyAmount, energyConsumption, distance);

                System.out.println("Animal added: " + animalType + ", Name: " + name + ", Speed: " + speed +
                        ", Energy Amount: " + energyAmount + ", Energy Consumption: " + energyConsumption +
                        ", Distance: " + distance + ", Initial Position: (" + initialX + ", " + initialY + ")");
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    private boolean isCompetitionTypeValidForAnimal(String animalType, String competitionType) {
        switch (animalType) {
            case "Cat":
            case "Dog":
            case "Snake":
                return competitionType.equals("Race");
            case "Dolphin":
            case "Whale":
                return competitionType.equals("Swimming");
            case "Eagle":
            case "Pigeon":
                return competitionType.equals("Jumping");
            case "Alligator":
                return competitionType.equals("Race") || competitionType.equals("Swimming");
            default:
                return false;
        }
    }

    private Animal createAnimal(String animalType, String name, double speed, double energyAmount, double energyConsumption, double distance) {
        switch (animalType) {
            case "Cat":
                Cat Cat=new Cat(name, speed, energyAmount, energyConsumption, distance);
                Cat.setCategory("Terrest");
                Cat.setEnergyPerMeter(100);
                return Cat;
            case "Dog":
                Dog Dog=new Dog(name, speed, energyAmount, energyConsumption, distance);
                Dog.setCategory("Terrest");
                Dog.setEnergyPerMeter(10);
                return Dog;
            case "Eagle":
                Eagle Eagle=new Eagle(name, speed, energyAmount, energyConsumption, distance);
                Eagle.setCategory("Air");
                return Eagle;
            case "Alligator":
                Alligator alligator = new Alligator(name, speed, energyAmount, energyConsumption, distance);
                alligator.setCategory(competitionType.equals("Swimming") ? "Water" : "Terrest");
                return alligator;
            case "Dolphin":
                Dolphin dolphin=new Dolphin(name, speed, energyAmount, energyConsumption, distance);
                dolphin.setCategory("Water");
                return dolphin;
            case "Pigeon":
                Pigeon pigeon=new Pigeon(name, speed, energyAmount, energyConsumption, distance);
                pigeon.setCategory("Air");
                return pigeon;
            case "Snake":
                Snake snake=new Snake(name, speed, energyAmount, energyConsumption, distance);
                snake.setCategory("Terrest");
                return snake;
            case "Whale":
                Whale whale=new Whale(name, speed, energyAmount, energyConsumption, distance);
                whale.setCategory("Water");
                return whale;
            default:
                throw new IllegalArgumentException("Unknown animal type.");
        }
    }


    public Animal getAnimal() {
        return animal;
    }
}