package animals;

import Olympics.Medal;

public class Whale extends MarineAnimal {
    private String foodType;
    public Whale() {
        super();
        this.foodType = "";
    }
    public Whale(String name, Gender gender, double weight, double speed, double diveDepth,Medal[] medals, String foodType) {
        super(name, gender, weight, speed, medals, diveDepth);
        this.foodType = foodType;
    }
    public Whale(Whale other) {
        super(other);
        this.foodType = other.foodType;
    }


    public String toString() {
        return "Whale{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", diveDepth=" + diveDepth +
                ", position=" + position +
                ", foodType='" + foodType + '\'' +
                '}';
    }

    public void makeSound() {
        System.out.println("Animal " + this.name + "Splash");
    }




}
