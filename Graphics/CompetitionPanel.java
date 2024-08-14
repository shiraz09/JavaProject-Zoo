
package Graphics;
import animals.Animal;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Graphics.Competition;
import mobility.Orientation;
import mobility.Point;
import java.util.HashMap; // נוסיף את ה-import הזה
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CompetitionPanel extends JPanel {
    private BufferedImage backgroundImg;
    private List<Animal> animals ;
    private JButton addAnimalButton;
    private JFrame parentFrame;
    private Competition currentCompetition;
    private ScheduledExecutorService scheduler;
    private Timer competitionTimer;
    private ArrayList<String> competitionsList;
    private boolean competitionRunning = false;
    private  JButton startCompetitionButton;
    private JButton startStopButton;
    private List<Thread> threads;
    private Competition competition;
    private Map<Integer, String> groups;
    private Map<String, List<Animal>> groupToAnimalsMap;

    private int landStartX = 0, landStartY = 0;
    private int waterStartX = 20, waterStartY = 20;
    private int airStartX = 0, airStartY = 0;

    public CompetitionPanel() {

        setLayout(new BorderLayout());
        this.competitionsList = new ArrayList<>();
        this.groupToAnimalsMap = new HashMap<>();
        this.animals = new ArrayList<>();
        this.threads = new ArrayList<>();
        this.groups = new HashMap<>();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 6));
        JButton addCompetitionButton = new JButton("Add Competition");
        addCompetitionButton.addActionListener(e -> {
            CreateCompetitionDialog createCompetitionDialog = new CreateCompetitionDialog(parentFrame, this);
            createCompetitionDialog.setVisible(true);
        });

        try {
            backgroundImg = ImageIO.read(new File("src/PhotosGraphics2/competitionBackground.png")); // עדכן את הנתיב לתמונה שלך
        } catch (IOException e) {
            System.out.println("Cannot load background image.");
        }

        JButton addAnimalButton = new JButton("Add Animal");
        addAnimalButton.addActionListener(e -> {
            AddAnimalDialog addAnimalDialog = new AddAnimalDialog(parentFrame,this);
            addAnimalDialog.setVisible(true);
            Animal newAnimal = addAnimalDialog.getAnimal();

            if (newAnimal != null) {
                newAnimal.loadImages(newAnimal.getType()); /** Loading the animal pictures **/
                newAnimal.resetDistance();
                addAnimal(newAnimal); /** Adds the animal to the competition panel **/
            }
        });

        startStopButton = new JButton("Start Competition");
        startStopButton.addActionListener(e -> toggleCompetition());
        buttonPanel.add(startStopButton);

        //add(addAnimalButton, BorderLayout.SOUTH);
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            stopCompetition();
            animals.clear();
            threads.clear();
            repaint();
        });

        JButton eatButton = new JButton("Eat");
        eatButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter animal index and energy amount (e.g., 0, 50):");
            if (input != null && !input.isEmpty()) {
                String[] parts = input.split(",");
                if (parts.length == 2) {
                    try {
                        int index = Integer.parseInt(parts[0].trim());
                        int energyAmount = Integer.parseInt(parts[1].trim());
                        if (index >= 0 && index < animals.size()) {
                            Animal animal = animals.get(index);
                            if (animal.isVisible()) { // בדיקה אם החיה גלויה
                                animal.eat(energyAmount); // מניחים שיש מתודה eat במחלקת Animal
                                JOptionPane.showMessageDialog(this, "Animal fed.");
                                System.out.println("Animal " + index + " energy updated to: " + animal.getCurrentEnergy());
                            } else {
                                JOptionPane.showMessageDialog(this, "Animal is no longer visible and cannot be fed.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid animal index.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid input format.");
                }
            }
        });

        JButton infoButton = new JButton("Info");
        infoButton.addActionListener(e -> {
            if (!animals.isEmpty()) {
                String[] columnNames = {"Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"};
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

                for (Animal animal : animals) {
                    Object[] rowData = {
                            animal.getAnimaleName(),
                            animal.getCategory(),  /** Introducing the category **/
                            animal.getType(),
                            animal.getSpeed(),
                            animal.getCurrentEnergy(), /** Make sure you read the current energy **/
                            animal.getDistance(),
                            animal.getEnergyConsumption()
                    };
                    tableModel.addRow(rowData);
                }

                JTable table = new JTable(tableModel);
                JScrollPane scrollPane = new JScrollPane(table);
                JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Animal Info", true);
                dialog.add(scrollPane);
                dialog.setSize(600, 400);
                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No animals to display.");
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        buttonPanel.add(addCompetitionButton);
        buttonPanel.add(addAnimalButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(eatButton);
        buttonPanel.add(infoButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        scheduler = Executors.newScheduledThreadPool(10);
    }
    public void addGroup(String groupName) {
        groupToAnimalsMap.putIfAbsent(groupName, new ArrayList<>()); // הוספת הקבוצה למפת הקבוצות
        int newGroupId = groups.size() + 1;
        groups.put(newGroupId, groupName);
    }
    public Map<Integer, String> getGroups() {
        return groups;
    }




    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImg != null) {
            g.drawImage(backgroundImg, 0, 0, this.getWidth(), this.getHeight(), null);
        }

        synchronized (animals) {
            for (Animal animal : animals) {
                if (animal.isVisible()) {
                    synchronized (animal) {
                        animal.drawObject(g);
                    }
                }
            }
        }
    }
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals); /** Make sure it returns a copy if animals list is mutable **/
    }

    /**
     * Method to add an animal to the competition
     * Sets the animal's position based on its category and adds it to the list of animals.
     *
     * @param animal The animal to be added.
     **/

    public void addAnimal(Animal animal) {
        String category = animal.getCategory().toLowerCase();

        switch (category) {
            case "terrest":
                animal.setPosition(new Point(landStartX, landStartY));
                landStartY += 20; // Update for next land animal
                break;
            case "water":
                animal.setPosition(new Point(waterStartX, waterStartY));
                waterStartY += 20; // Update for next water animal
                break;
            case "air":
                animal.setPosition(new Point(airStartX, airStartY));
                airStartY += 20; // Update for next air animal
                break;
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }

        animals.add(animal);
        repaint();
    }



    protected void startCompetition() {
        competitionRunning = true;
        for (Animal animal : animals) {
            Thread thread = new Thread(() -> {
                while (competitionRunning && animal.getCurrentEnergy() > 0) {
                    animal.move();
                    // עדכון מיקום החיה
                    updateAnimalPosition(animal);
                    try {
                        Thread.sleep(500); // מרווח זמן לעדכון
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
                repaint();
            });
            thread.start();
            threads.add(thread);
        }
        competitionTimer = new Timer(100, event -> repaint());
        competitionTimer.start();
    }

    /**
     * Method to stop the competition
     * Interrupts all running threads associated with the competition.
     **/
    private void stopCompetition() {
        competitionRunning = false;
        for (Thread thread : threads) {
            thread.interrupt();
        }threads.clear();

        // עצירת הטיימר
        if (competitionTimer != null) {
            competitionTimer.stop();
        }
    }
    /**
     * Method to toggle the state of the competition
     * Starts or stops the competition based on its current state.
     **/
    private void toggleCompetition() {
        if (competitionRunning) {
            stopCompetition();
            startStopButton.setText("Start Competition");
        } else {
            startCompetition();
            startStopButton.setText("Stop Competition");
        }
    }




    public void addAnimalToGroup(String groupName, Animal animal) {
        synchronized (animal) {
            groupToAnimalsMap.putIfAbsent(groupName, new ArrayList<>());
            groupToAnimalsMap.get(groupName).add(animal);
        }
    }

    // Method to get the animals in a specific group
    public List<Animal> getAnimalsInGroup(String groupName) {
        return groupToAnimalsMap.getOrDefault(groupName, new ArrayList<>());
    }

    public Map<String, List<Animal>> getGroupToAnimalsMap() {
        return groupToAnimalsMap;
    }


    public void startRegularCompetition() {
        competitionRunning = true;
        for (Animal animal : animals) {
            Thread thread = new Thread(() -> {
                while (competitionRunning && animal.isActive()) {
                    moveAnimal(animal);  // Move the animal
                    try {
                        Thread.sleep(500); // מרווח זמן לעדכון התנועה
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }
    }

    public void startRelayCompetition() {
        competitionRunning = true;
        for (Animal animal : animals) {
            Thread thread = new Thread(() -> {
                while (competitionRunning && animal.isActive()) {
                    moveAnimal(animal);  // Move the animal
                    try {
                        Thread.sleep(500); // מרווח זמן לעדכון התנועה
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            thread.start();
            threads.add(thread);
        }
    }

    private void moveAnimal(Animal animal) {
        if (animal.isActive()) {
            animal.move();
            animal.consumeEnergy();
            updateAnimalPosition(animal);
            repaint();
        }
    }
    private void updateAnimalPosition(Animal animal) {
        Point currentPosition = animal.getPosition(); // קבלת המיקום הנוכחי של החיה
        Point newPosition = new Point(currentPosition.getX(), currentPosition.getY());

        switch (animal.getOrientation()) {
            case EAST:
                newPosition.setX((int) (newPosition.getX() + animal.getSpeed()));
                if (newPosition.getX() > getWidth() - animal.getSize()) {
                    if ("Water".equals(animal.getCategory()) || "Air".equals(animal.getCategory())) {
                        newPosition.setX(getWidth() - animal.getSize());
                        animal.setActive(false); // עצירת החיה אם היא מגיעה לגבול המסך
                    } else {
                        newPosition.setX(getWidth() - animal.getSize());
                        animal.setOrientation(Orientation.SOUTH);
                    }
                }
                break;
            case SOUTH:
                newPosition.setY((int) (newPosition.getY() + animal.getSpeed()));
                if (newPosition.getY() > getHeight() - animal.getSize()) {
                    newPosition.setY(getHeight() - animal.getSize());
                    animal.setOrientation(Orientation.WEST);
                }
                break;
            case WEST:
                newPosition.setX((int) (newPosition.getX() - animal.getSpeed()));
                if (newPosition.getX() < 0) {
                    newPosition.setX(0);
                    animal.setOrientation(Orientation.NORTH);
                }
                break;
            case NORTH:
                newPosition.setY((int) (newPosition.getY() - animal.getSpeed()));
                if (newPosition.getY() < 0) {
                    newPosition.setY(0);
                    animal.setOrientation(Orientation.EAST);
                }
                break;
        }

        animal.setPosition(newPosition); // עדכון המיקום של החיה
        revalidate();
        repaint(); // טריגר לציור מחדש של הפאנל עם המיקום החדש
    }



}