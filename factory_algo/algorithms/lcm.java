package algorithms;

public class lcm extends MathAlgorithm {

    public void print() {
        System.out.println("Finding LCM in progress ...");
    }

    int hcf(int a, int b) {
        if (b == 0)
            return a;
        return hcf(b, a % b);
    }

    public int execute(int a, int b) {
        return (a * b) / hcf(a, b);
    }
}