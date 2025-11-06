package testing.demo;

public class Calculator {

    public int add(int value1, int value2){
        return value1 + value2;
    }
    public int substract(int value1, int value2) {

        return value1 - value2;

    }
    public int multiply(int value1, int value2) {

        return value1 * value2;

    }
    public int divide(int value1, int value2){


        if(value2==0){
            throw new IllegalArgumentException("Divide by zero");
        }
        return value1/value2;
    }
}
