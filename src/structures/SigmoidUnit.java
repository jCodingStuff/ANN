package structures;

public class SigmoidUnit {

    public static double sigmoid(double x) {
        return 1/(1 + Math.pow(Math.E,-x));
    }

    public static double dsigmoid(double x) {
        return sigmoid(x) * (1 - sigmoid(x));
    }

}
