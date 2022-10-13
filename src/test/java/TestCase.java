import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.*;

import java.io.IOException;

public class TestCase {

    CsvFileProcessor csvFileProcessor;
    CalculatorMean calculatorMean;
    CalculatorMedian calculatorMedian;
    CalculatorPopularScore calculatorPopularScore;
    CalculatorScoreWithFrequencies calculatorScoreWithFrequencies;

    @BeforeEach
    void init(){
        csvFileProcessor = new CsvFileProcessor();
        calculatorMean = new CalculatorMean();
        calculatorMedian = new CalculatorMedian();
        calculatorPopularScore = new CalculatorPopularScore();
        calculatorScoreWithFrequencies = new CalculatorScoreWithFrequencies();
    }

    // positive test case file is found
    @Test
    void readFileSuccess(){
        String filePath = "/home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/data_sekolah.csv";
        boolean fileIsFound;
        try{
            csvFileProcessor.readFile(filePath);
            fileIsFound = true;
        }catch (IOException ioe){
            fileIsFound = false;
        }
        Assertions.assertTrue(fileIsFound);
    }

    // negative test case no file
    @Test
    void readFileWrongDirectory() {
        String filePath = "lalala/lelele/lololo";
        Assertions.assertThrows(IOException.class, () -> csvFileProcessor.readFile(filePath));
    }

    // negative test case, score is not a number
    @Test
    void readFileWithScoreIsNotNumberFounded(){
        String filePath = "/home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/data_sekolah_2.csv";
        boolean scoreIsNotANumberFound = false;
        try{
            csvFileProcessor.readFile(filePath);
        }catch (NumberFormatException nfe){
            scoreIsNotANumberFound = true;
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        Assertions.assertTrue(scoreIsNotANumberFound);
    }

    // positive test case calculator mean
    @Test
    void calculatorMeanWork(){
        String result;
        int [] tempArray = {1, 2, 3};
        result = calculatorMean.calculate(tempArray);
        Assertions.assertEquals("2.0", result);
    }

    // negative test case calculator mean
    @Test
    void calculatorMeanOnNullArray(){
        String result;
        int [] tempArray = new int[0];
        result = calculatorMean.calculate(tempArray);
        Assertions.assertEquals("NaN", result);
    }

    // positive test case calculator median
    @Test
    void calculatorMedianWork(){
        String result;
        int [] tempArray = {1, 2, 3};
        result = calculatorMedian.calculate(tempArray);
        Assertions.assertEquals("2", result);
    }

    // negative test case calculator median
    @Test
    void calculatorMedianOnNullArray(){
        String result;
        int [] tempArray = new int[0];
        result = calculatorMedian.calculate(tempArray);
        Assertions.assertEquals("Exception: array nilai siswa kosong", result);
    }

    // positive test case calculator popular score
    @Test
    void calculatorPopularScoreWork(){
        String result;
        int [] tempArray = {5, 5, 5, 9, 1, 2, 4, 4, 3, 7, 8, 6, 2};
        result = calculatorPopularScore.calculate(tempArray);
        Assertions.assertEquals("5", result);
    }

    // negative test case calculator popular score on array null
    @Test
    void calculatorPopularScoreOnNullArray(){
        String result;
        int [] tempArray = new int[0];
        result = calculatorPopularScore.calculate(tempArray);
        Assertions.assertEquals("Exception: array nilai siswa kosong", result);
    }

    // positive test case calculator score with frequencies
    @Test
    void calculatorScoreWithFrequenciesWork(){
        String result;
        int [] tempArray = {1, 3, 4, 1, 5, 5, 8, 9, 1, 4};
        result = calculatorScoreWithFrequencies.calculate(tempArray);
        String expectedResult = "Nilai: 1, frekuensi: 3\n" +
                "Nilai: 3, frekuensi: 1\n" +
                "Nilai: 4, frekuensi: 2\n" +
                "Nilai: 5, frekuensi: 2\n" +
                "Nilai: 8, frekuensi: 1\n" +
                "Nilai: 9, frekuensi: 1\n";
        Assertions.assertEquals(expectedResult, result);
    }

    // negative test case calculator score with frequencies on null array
    @Test
    void calculatorScoreWithFrequenciesOnNullArray(){
        String result;
        int [] tempArray = new int[0];
        result = calculatorScoreWithFrequencies.calculate(tempArray);
        Assertions.assertEquals("", result);
    }
}
