package system;

import Olympics.Medal;
import animals.*;

import java.util.Scanner;

public class Sys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of animals: ");
        int numOfAnimals = scanner.nextInt();
        Animal[] animals = new Animal[numOfAnimals];

        for (int i = 0; i < numOfAnimals; i++) {
            System.out.println("Choose type of animal:");
            System.out.println("1. Land");
            System.out.println("2. Marine");
            System.out.println("3. Aerial");

            int typeChoice = scanner.nextInt();
            switch (typeChoice) {
                case 1:
                    System.out.println("Choose Land animal:");
                    System.out.println("1. Dog");
                    System.out.println("2. Cat");
                    System.out.println("3. Snake");
                    int LandChoice = scanner.nextInt();
                    animals[i] = createLandAnimal(scanner, LandChoice);
                    break;
                case 2:
                    System.out.println("Choose Marine animal:");
                    System.out.println("1. Alligator");
                    System.out.println("2. Whale");
                    System.out.println("3. Dolphin");
                    int MarineChoice = scanner.nextInt();
                    animals[i] = createMarineAnimal(scanner, MarineChoice);
                    break;
                case 3:
                    System.out.println("Choose aerial animal:");
                    System.out.println("1. Eagle");
                    System.out.println("2. Pigeon");
                    int aerialChoice = scanner.nextInt();
                    animals[i] = createAerialAnimal(scanner, aerialChoice);
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    i--;
                    break;
            }
        }
        boolean Menu2 = true;
        while (Menu2) {
            System.out.println("Choose an action:");
            System.out.println("1. Show information about all animals");
            System.out.println("2. Make all animals sound");
            System.out.println("3. Exit");

            int actionChoice = scanner.nextInt();
            switch (actionChoice) {
                case 1:
                    for (Animal animal : animals) {
                        System.out.println(animal);
                    }
                    break;
                case 2:
                    for (Animal animal : animals) {
                        animal.makeSound();
                    }
                    break;
                case 3:
                    Menu2 = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        }

    }

    private static Animal createMarineAnimal(Scanner scanner, int choice) {
        scanner.nextLine();
        Medal[] medals = new Medal[0];
        switch (choice) {
            case 1:
                System.out.println("Enter alligator's name: ");
                String alligatorName = scanner.nextLine();
                System.out.println("Enter the gender of the alligator:MALE, FEMALE, HERMAPHRODITE ");
                Animal.Gender alligatorGender = Animal.Gender.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter alligator's weight: (0-500[kg])");
                double alligatorWeight = scanner.nextDouble();
                System.out.print("Enter alligator's speed: (0-80[km/h])");
                double alligatorSpeed = scanner.nextDouble();
                System.out.print("Enter alligator's dive depth: (0-30[m]) ");
                double diveDepth = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter alligator's area of living: (stream/river/swamp/other)");
                String areaOfLiving = scanner.nextLine();
                return new Alligator(alligatorName, alligatorGender, alligatorWeight, alligatorSpeed, diveDepth, medals, areaOfLiving);
            case 2:
                System.out.print("Enter whale's name: ");
                String whaleName = scanner.nextLine();
                System.out.print("Enter whale's gender :MALE, FEMALE, HERMAPHRODITE ");
                Animal.Gender whaleGender = Animal.Gender.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter whale's weight:(0-70[T]) ");
                double whaleWeight = scanner.nextDouble();
                System.out.print("Enter whale's speed: (0-50[km/h])");
                double whaleSpeed = scanner.nextDouble();
                System.out.print("Enter whale's dive depth: (0-600[m]) ");
                double whaleDiveDepth = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter whale's food type: (fish/alligator/other) ");
                String foodType = scanner.nextLine();
                return new Whale(whaleName, whaleGender, whaleWeight, whaleSpeed, whaleDiveDepth, medals, foodType);
            case 3:
                System.out.print("Enter dolphin's name: ");
                String dolphinName = scanner.nextLine();
                System.out.print("Enter dolphin's gender (MALE, FEMALE, HERMAPHRODITE): ");
                Animal.Gender dolphinGender = Animal.Gender.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter dolphin's weight: (0-200[kg])");
                double dolphinWeight = scanner.nextDouble();
                System.out.print("Enter dolphin's speed: (0-60[km/h])");
                double dolphinSpeed = scanner.nextDouble();
                System.out.print("Enter dolphin's dive depth:(0-300[m]) ");
                double dolphinDiveDepth = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter dolphin's water type (SEA/SWEET): ");
                Dolphin.WaterType waterType = Dolphin.WaterType.valueOf(scanner.nextLine().toUpperCase());
                return new Dolphin(dolphinName, dolphinGender, dolphinWeight, dolphinSpeed, medals, dolphinDiveDepth, waterType);
            default:
                System.out.println("Invalid choice, returning null.");
                return null;


        }
    }

    private static Animal createLandAnimal(Scanner scanner, int choice) {
        scanner.nextLine();
        Medal[] medals = new Medal[0];
        switch (choice) {
            case 1:
                System.out.print("Enter dog's name: ");
                String dogName = scanner.nextLine();
                System.out.print("Enter dog's gender (MALE, FEMALE, HERMAPHRODITE): ");
                Animal.Gender dogGender = Animal.Gender.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter dog's weight: (0-110[kg]) ");
                double dogWeight = scanner.nextDouble();
                System.out.print("Enter dog's speed (0-80[km/h]): ");
                double dogSpeed = scanner.nextDouble();
                System.out.print("Enter dog's number of legs (0-4): ");
                int dogNoLegs = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter dog's breed: (Bulldog/Labador/other)");
                String breed = scanner.nextLine();
                return new Dog(dogName, dogGender, dogWeight, dogSpeed, medals, dogNoLegs, breed);

            case 2:
                System.out.print("Enter cat's name: ");
                String catName = scanner.nextLine();
                System.out.print("Enter cat's gender (MALE, FEMALE, HERMAPHRODITE): ");
                Animal.Gender catGender = Animal.Gender.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter cat's weight: (0-110[kg]) ");
                double catWeight = scanner.nextDouble();
                System.out.print("Enter cat's speed: (0-80[km/h]) ");
                double catSpeed = scanner.nextDouble();
                System.out.print("Enter cat's number of legs:(0-4)");
                int catNoLegs = scanner.nextInt();
                System.out.print("Enter if the cat is castrated: yes/no");
                boolean catCastrated = scanner.nextBoolean();
                return new Cat(catName, catGender, catWeight, catSpeed, medals, catNoLegs, catCastrated);

            case 3:
                System.out.print("Enter snake's name: ");
                String snakeName = scanner.nextLine();
                System.out.print("Enter snake's gender (MALE, FEMALE, HERMAPHRODITE): ");
                Animal.Gender snakeGender = Animal.Gender.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter snake's weight: (0-110[kg]) ");
                double snakeWeight = scanner.nextDouble();
                System.out.print("Enter snake's speed: (0-80[km/h])");
                double snakeSpeed = scanner.nextDouble();
                System.out.print("Enter snake's number of legs: (0-4) ");
                int snakeNoLegs = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter what is the poisonous level of the snake?:NON_POISONOUS/MILDLY_POISONOUS/HIGHLY_POISONOUS ");
                Snake.Poisonous poisonous = Snake.Poisonous.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter the length of the snake: (0-300) ");
                double snakelength = scanner.nextDouble();
                return new Snake(snakeName, snakeGender, snakeWeight, snakeSpeed, medals, snakeNoLegs, poisonous, snakelength);

            default:
                System.out.println("Invalid choice, returning null.");
                return null;


        }
    }

    private static Animal createAerialAnimal(Scanner scanner, int choice) {
        scanner.nextLine();
        Medal[] medals = new Medal[0];
        switch (choice) {
            case 1:
                System.out.print("Enter eagle's name: ");
                String eagleName = scanner.nextLine();
                System.out.print("Enter eagle's gender (MALE, FEMALE, HERMAPHRODITE): ");
                Animal.Gender eagleGender = Animal.Gender.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter eagle's weight: (0-110[kg])");
                double eagleWeight = scanner.nextDouble();
                System.out.print("Enter eagle's speed: (0-180[km/h])");
                double eagleSpeed = scanner.nextDouble();
                System.out.print("Enter eagle's wingspan: (0[m]-3[m])");
                double wingspan = scanner.nextDouble();
                System.out.print("Enter eagle's altitude of flight: (300[m])");
                double altitudeOfFlight = scanner.nextDouble();
                return new Eagle(eagleName, eagleGender, eagleWeight, eagleSpeed, medals, wingspan, altitudeOfFlight);

            case 2:
                System.out.print("Enter pigeon's name: ");
                String pigeonName = scanner.nextLine();
                System.out.print("Enter pigeon's gender (MALE, FEMALE, HERMAPHRODITE): ");
                Animal.Gender pigeonGender = Animal.Gender.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Enter pigeon's weight: (0-110[kg]) ");
                double pigeonWeight = scanner.nextDouble();
                System.out.print("Enter pigeon's speed:  (0-60[km/h]) ");
                double pigeonSpeed = scanner.nextDouble();
                System.out.print("Enter pigeon's wingspan: (0[m]-3[m]) ");
                double pigeonWingspan = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter pigeon's family:(Antwerp smerle/IcePigeon/other ");
                String family = scanner.nextLine();
                return new Pigeon(pigeonName, pigeonGender, pigeonWeight, pigeonSpeed, medals, pigeonWingspan, family);
            default:
                System.out.println("Invalid choice, returning null.");
                return null;

        }
    }
}
