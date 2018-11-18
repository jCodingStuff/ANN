import structures.NeuralNetwork;
import trainers.Backpropagation;
import trainers.NNTrainer;

public class Test {

    public static void main(String[] args) {

        double[][] data = new double[][]{{1,1},{1,0},{0,1},{0,0}};
        double[][] targets = new double[][]{{0},{1},{1},{0}};
        double lr = 0.1;
        double threshold = 0.0005;

        int[] layers = new int[]{2,3,3,1};
        NeuralNetwork net = new NeuralNetwork(layers);

        NNTrainer trainer = new Backpropagation(data, targets, threshold, lr);
        trainer.train(net);

        net.test(data,targets);

    }

}
