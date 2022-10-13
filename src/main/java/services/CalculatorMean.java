package services;

import java.util.Arrays;

public class CalculatorMean extends CalculatorMode {
    @Override
    public String calculate(int [] scoreArray){
        return Double.toString(Arrays.stream(scoreArray).average().orElse(Double.NaN));
    }
}
