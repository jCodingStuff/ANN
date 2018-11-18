package cmath;

public class ArrayFunctions {

    public static double[] append(double[] arr, double value) {
        double[] newArr = new double[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[newArr.length-1] = value;
        return newArr;
    }

    public static String arrToStr(double[] arr) {
        String result = "[";
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
            if (i != arr.length-1) result += ", ";
        }
        return result + "]";
    }

}
