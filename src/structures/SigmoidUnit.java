package structures;

import cmath.MFunctions;
import cmath.RandomGen;

public class SigmoidUnit {

    private double[] weights;
    private double output;

    public SigmoidUnit(int inputs) {
        this.weights = new double[inputs];
    }

    public void initWeights(double init) {
        for (int i = 0; i < this.weights.length; i++) {
            this.weights[i] = RandomGen.randomRange(-init,init);
        }
    }

    public void produceOutput(double[] inputs) {
        double result = 0;
        for (int i = 0; i < inputs.length; i++) {
            result += inputs[i] * this.weights[i];
        }
        this.output = MFunctions.sigmoid(result);
    }

    public double getOutput() {
        return this.output;
    }

    public double[] getWeights() {
        return this.weights;
    }

}
