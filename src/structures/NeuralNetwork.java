package structures;

import cmath.ArrayFunctions;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {

    private static final double BIAS = 1;

    private List<SigmoidUnit[]> layers;

    public NeuralNetwork(int[] layers) {
        this.layers = new ArrayList<>();
        for (int i = 1; i < layers.length; i++) {
            SigmoidUnit[] newLayer = new SigmoidUnit[layers[i]];
            for (int j = 0; j < newLayer.length; j++) {
                newLayer[j] = new SigmoidUnit(layers[i-1] + 1); // Inputs plus the bias
            }
            this.layers.add(newLayer);
        }
    }

    public void initWeights() {
        // Hidden layers
        for (int i = 0; i < this.layers.size()-1; i++) {
            SigmoidUnit[] cLayer = this.layers.get(i);
            double nIn = cLayer[0].getWeights().length; // Inputs and bias
            double nOut = this.layers.get(i+1).length; // All output connections
            double init = this.getInit(nIn,nOut);
            for (int j = 0; j < cLayer.length; j++) {
                cLayer[j].initWeights(init);
            }
        }

        // Output layer
        int lastIndex = this.layers.size() - 1;
        SigmoidUnit[] lastLayer = this.layers.get(lastIndex);
        double nIn = lastLayer[0].getWeights().length; // Inputs and bias
        double nOut = lastLayer.length; // All output connections
        double init = this.getInit(nIn,nOut);
        for (int k = 0; k < lastLayer.length; k++) {
            lastLayer[k].initWeights(init);
        }
    }

    public double[] feedForward(double[] inputs) {
        double newInputs[] = ArrayFunctions.append(inputs,BIAS);
        for (int i = 0; i < this.layers.size(); i++) {
            SigmoidUnit[] cLayer = this.layers.get(i);
            double[] outputs = new double[cLayer.length]; // Plus the bias
            if (i != this.layers.size()-1) {
                ArrayFunctions.append(outputs,BIAS);
            }
            for (int j = 0; j < cLayer.length; j++) {
                cLayer[j].produceOutput(newInputs);
                outputs[j] = cLayer[j].getOutput();
            }
            newInputs = outputs;
        }
        return newInputs;
    }

    private double getInit(double nIn, double nOut) {
        return Math.sqrt(6)/Math.sqrt(nIn + nOut);
    }

    public List<SigmoidUnit[]> getLayers() {
        return this.layers;
    }

    public void test(double[][] data, double[][] targets) {
        for (int i = 0; i < data.length; i++) {
            double[] input = data[i];
            double[] target = targets[i];
            double[] output = this.feedForward(input);
            System.out.println("Input  -> " + ArrayFunctions.arrToStr(input));
            System.out.println("Target -> " + ArrayFunctions.arrToStr(target));
            System.out.println("Output -> " + ArrayFunctions.arrToStr(output) + "\n");
        }
    }

}
