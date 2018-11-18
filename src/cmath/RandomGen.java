package cmath;

public class RandomGen {

    public static double randomRange(double min, double max) {
        return (Math.random() * (max - min)) - min;
    }

}
