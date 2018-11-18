import cmath.MFunctions;
import structures.NeuralNetwork;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        int[] layers = new int[]{2,1};
        NeuralNetwork net = new NeuralNetwork(layers);
        net.initWeights();
        double[] inputs = new double[]{1,1};
        System.out.println(Arrays.toString(net.feedForward(inputs)));

    }

}
