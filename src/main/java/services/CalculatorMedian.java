package services;

import java.util.stream.IntStream;

public class CalculatorMedian extends CalculatorMode {
    @Override
    public String calculate(int[] scoreArray) {
        try{
            return Integer.toString(IntStream.of(scoreArray).sorted().toArray()[scoreArray.length / 2]);
        }catch (ArrayIndexOutOfBoundsException e){
            return "Exception: array nilai siswa kosong";
        }
    }
}
