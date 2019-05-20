package algorithms;
import java.lang.Math;

public class power extends MathAlgorithm {

    public void print(){
        System.out.println("Finding power in progress ...");
    }

    public int execute(int a, int b) {
        return (int)Math.pow(a, b);
    }
}
