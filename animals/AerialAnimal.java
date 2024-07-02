package animals;

import Olympics.Medal;
import mobility.Point;
import java.util.Arrays;

public abstract class AerialAnimal extends Animal{
    protected double wingspan;
    public AerialAnimal() {
        super();
        this.wingspan = 0;
    }
    public AerialAnimal(String name, Gender gender, double weight, double speed, Medal[] medals, double wingspan){
        super(new Point(0,100),name,gender,weight,speed,medals,new Point(0,100));
        this.wingspan=wingspan;
    }
    public AerialAnimal(AerialAnimal other) {
        super(other);
        this.wingspan = other.wingspan;
    }

    public String toString() {
        return "AirAnimal{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", speed=" + speed +
                ", medals=" + Arrays.toString(medals) +
                ", wingspan=" + wingspan +
                ", position=" + position +
                '}';
    }

}
