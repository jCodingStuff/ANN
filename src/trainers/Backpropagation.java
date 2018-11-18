package trainers;

import structures.NeuralNetwork;

/**
 * This trainer uses pure backpropagation algorithm and stops whenever the squared error is less than threshold
 */
public class Backpropagation implements NNTrainer {

    private double[][] data;
    private double[][] targets;
    private double threshold;

    public Backpropagation(double[][] data, double[][] targets, double threshold) {
        this.data = data;
        this.targets = targets;
        this.threshold = threshold;
    }

    @Override
    public void train(NeuralNetwork net) {

    }

    private double getSquaredError(NeuralNetwork net) {
        double error = 0;
        for (int i = 0; i < data.length; i++) {
            double[] outputs = net.feedForward(data[i]);
            double[] target = this.targets[i];
        }
        return error * 0.5;
    }
}
