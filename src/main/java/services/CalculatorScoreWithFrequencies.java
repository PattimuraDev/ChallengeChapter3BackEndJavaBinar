package services;

import java.util.HashMap;
import java.util.Map;

public class CalculatorScoreWithFrequencies extends CalculatorMode {
    @Override
    public String calculate(int[] scoreArray) {
        try{
            StringBuilder stringBuilder = new StringBuilder();
            Map<Integer, Integer> scoreFrequenciesMapping = new HashMap<>();

            // Mengisi map dengan key-value berupa nilai-frekuensi
            for (int i = 0; i < scoreArray.length; i++) {
                if (i == 0) {
                    scoreFrequenciesMapping.put(scoreArray[i], 1);
                } else {
                    if (scoreFrequenciesMapping.containsKey(scoreArray[i])) {
                        scoreFrequenciesMapping.put(scoreArray[i], scoreFrequenciesMapping.get(scoreArray[i]) + 1);
                    } else {
                        scoreFrequenciesMapping.put(scoreArray[i], 1);
                    }
                }
            }

            // Membaca data dari hashmap yang sudah berisi nilai-frekuensi
            for (Map.Entry<Integer, Integer> mapSet : scoreFrequenciesMapping.entrySet()) {
                stringBuilder.append("Nilai: ")
                        .append(mapSet.getKey())
                        .append(", frekuensi: ")
                        .append(mapSet.getValue())
                        .append("\n");
            }
            return stringBuilder.toString();
        }catch (ArrayIndexOutOfBoundsException e){
            return "Exception: array nilai siswa kosong";
        }
    }
}
