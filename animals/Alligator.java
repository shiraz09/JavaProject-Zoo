package animals;


import Olympics.Medal;

public class Alligator extends MarineAnimal {
    private String AreaOfLiving;
    public Alligator() {
        super();
        this.AreaOfLiving = "";
    }
    public Alligator(String name, Gender gender, double weight, double speed, double diveDepth, Medal[] medals, String areaOfLiving) {
        super(name, gender, weight, speed, medals, diveDepth);
        this.AreaOfLiving = areaOfLiving;
    }
    public Alligator(Alligator other) {
        super(other);
        this.AreaOfLiving = other.AreaOfLiving;
    }
    public String toString() {
        return "Alligator{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + java.util.Arrays.toString(medals) +
                ", diveDepth=" + diveDepth +
                ", position=" + position +
                ", areaOfLiving='" + AreaOfLiving + '\'' +
                '}';
    }

    public void makeSound() {
        System.out.println("Animal " + this.name + "Roar");
    }




}
