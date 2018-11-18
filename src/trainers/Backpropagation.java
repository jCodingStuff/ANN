package trainers;

import structures.NeuralNetwork;
import structures.SigmoidUnit;

import java.util.List;

/**
 * This trainer uses pure Backpropagation algorithm and stops whenever the squared error is less than threshold
 */
public class Backpropagation implements NNTrainer {

    private double[][] data;
    private double[][] targets;
    private double threshold;
    private double lr;

    public Backpropagation(double[][] data, double[][] targets, double threshold, double lr) {
        this.data = data;
        this.targets = targets;
        this.threshold = threshold;
        this.lr = lr;
    }

    @Override
    public void train(NeuralNetwork net) {
        // Initialize weights in the neural network
        net.initWeights();

        int iterations = 0;

        // Until termination condition is met, learn
        double err = this.getSquaredError(net);
        while (err >= threshold) {

            // Use every training instance to learn
            for (int i = 0; i < this.data.length; i++) {

                // Produce output
                double[] input = this.data[i];
                double[] output = net.feedForward(input);
                double[] target = this.targets[i];

                // Compute error term for output layer
                this.lastLayerError(net,output,target);
                // Computer error term for hidden layers
                this.hiddenLayerError(net);

                // Update weights for first layer
                this.updateWeightsFirst(net,input);
                // Update weights for the rest of layers
                this.updateWeightsRest(net);

            }

            err = this.getSquaredError(net);
            iterations++;
//            if (iterations % 10000 == 0) {
//                System.out.println("Iterations    -> " + iterations);
//                System.out.println("Squared Error -> " + err + "\n");
//            }

        }

        System.out.println("Final Iterations    -> " + iterations);
        System.out.println("Final Squared Error -> " + err + "\n");

    }

    private void lastLayerError(NeuralNetwork net, double[] output, double[] target) {
        SigmoidUnit[] layer = net.getLayers().get(net.getLayers().size()-1);
        for (int i = 0; i < layer.length; i++) {
            layer[i].setErrorTerm(output[i]*(1-output[i])*(target[i]-output[i]));
        }
    }

    private void hiddenLayerError(NeuralNetwork net) {
        List<SigmoidUnit[]> layers = net.getLayers();
        for (int i = layers.size()-2; i >= 0; i--) {
            SigmoidUnit[] layer = layers.get(i);
            SigmoidUnit[] successors = layers.get(i+1);
            for (int j = 0; j < layer.length; j++) {
                double sum = 0;
                for (int k = 0; k < successors.length; k++) {
                    sum += successors[k].getErrorTerm() * successors[k].getWeights()[j];
                }
                double out = layer[j].getOutput();
                layer[j].setErrorTerm(out * (1 - out) * sum);
            }
        }
    }

    private void updateWeightsFirst(NeuralNetwork net, double[] inputs) {
        SigmoidUnit[] layer = net.getLayers().get(0);
        for (int j = 0; j < layer.length; j++) {
            for (int i = 0; i < inputs.length; i++) {
                double increment = this.lr * layer[j].getErrorTerm() * inputs[i];
                layer[j].getWeights()[i] += increment;
            }
            // Bias part
            layer[j].getWeights()[layer[j].getWeights().length-1] += this.lr * layer[j].getErrorTerm();
        }
    }

    private void updateWeightsRest(NeuralNetwork net) {
        List<SigmoidUnit[]> layers = net.getLayers();
        for (int k = 1; k < layers.size(); k++) {
            SigmoidUnit[] layer = layers.get(k);
            SigmoidUnit[] antecedents = layers.get(k-1);
            for (int j = 0; j < layer.length; j++) {
                for (int i = 0; i < antecedents.length; i++) {
                    double increment = this.lr * layer[j].getErrorTerm() * antecedents[i].getOutput();
                    layer[j].getWeights()[i] += increment;
                }
                // Bias part
                layer[j].getWeights()[layer[j].getWeights().length-1] += this.lr * layer[j].getErrorTerm();
            }
        }
    }

    private double getSquaredError(NeuralNetwork net) {
        double error = 0;
        for (int i = 0; i < this.data.length; i++) {
            double[] output = net.feedForward(this.data[i]);
            double[] target = this.targets[i];
            for (int j = 0; j < output.length; j++) {
                error += Math.pow(target[j]-output[j], 2);
            }
        }
        return error * 0.5;
    }
}
