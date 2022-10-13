package services;

public class CalculatorPopularScore extends CalculatorMode {
    @Override
    public String calculate(int[] scoreArray) {
        try{
            int counter = 1, temp, tempCounter;
            int modus = scoreArray[0];
            for (int i = 0; i < scoreArray.length - 1; i++) {
                temp = scoreArray[i];
                tempCounter = 1;
                for (int j = 1; j < scoreArray.length; j++) {
                    if (temp == scoreArray[j]) {
                        tempCounter++;
                    }
                }
                if (tempCounter > counter) {
                    modus = temp;
                    counter = tempCounter;
                }
            }
            return Integer.toString(modus);
        }catch (ArrayIndexOutOfBoundsException e){
            return "Exception: array nilai siswa kosong";
        }
    }
}
