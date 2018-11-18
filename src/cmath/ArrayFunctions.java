package cmath;

public class ArrayFunctions {

    public static double[] append(double[] arr, double value) {
        double[] newArr = new double[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr[arr.length-1] = value;
        return newArr;
    }

}
