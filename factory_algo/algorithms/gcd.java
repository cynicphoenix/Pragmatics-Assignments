package algorithms;

public class gcd extends MathAlgorithm {

    public void print(){
        System.out.println("Finding GCD in progress ...");
    }

    public int execute(int a, int b) {
        if (b == 0)
            return a;
        return execute(b, a % b);
    }
}
