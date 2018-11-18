package structures;

import cmath.RandomGen;

public class SigmoidUnit {

    private double[] weights;

    public SigmoidUnit(int inputs) {
        this.weights = new double[inputs];
    }

    public void initWeights(double init) {
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] = RandomGen.randomRange(-init, init);
        }
    }

    public double output(double[] inputs) {
        double result = 0;
        for (int i = 0; i < inputs.length; i++) {
            result += inputs[i] * this.weights[i];
        }
        return result;
    }

    public static double sigmoid(double x) {
        return 1/(1 + Math.pow(Math.E,-x));
    }

    public static double dsigmoid(double x) {
        return sigmoid(x) * (1 - sigmoid(x));
    }

}
