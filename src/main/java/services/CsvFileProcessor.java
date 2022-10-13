package services;

import model.DataKelas;
import java.io.*;
import java.util.*;

public class CsvFileProcessor implements FileProcessor{
    private final Map<String, DataKelas> mapDataKelas = new HashMap<>();
    private final CalculatorMedian calculatorMedian = new CalculatorMedian();
    private final CalculatorMean calculatorMean = new CalculatorMean();
    private final CalculatorPopularScore calculatorPopularScore = new CalculatorPopularScore();
    private final CalculatorScoreWithFrequencies calculatorScoreWithFrequencies = new CalculatorScoreWithFrequencies();

    @Override
    public void readFile(String fileLocation) throws IOException, NumberFormatException{
        try {
            File file = new File(fileLocation);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] tempArray;

            while ((line = bufferedReader.readLine()) != null) {
                // Memisahkan/split data dengan tanda ; pada file csv
                tempArray = line.split(";");
                DataKelas dataKelas = new DataKelas();
                int[] tempArrayDataNilai = new int[tempArray.length - 1];
                dataKelas.setNamaKelas(tempArray[0]);

                // mengambil kumpulan data nilai dari class/object DataKelas
                for (int i = 1; i < tempArray.length; i++) {
                    tempArrayDataNilai[i - 1] = Integer.parseInt(tempArray[i]);
                }
                dataKelas.setDataNilai(tempArrayDataNilai);
                mapDataKelas.put(tempArray[0], dataKelas);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void writeFile(int pilihan){
        final String pembatas = "---------------------------";
        final String fileLocationModusTxt = "/home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/modus_report.txt";
        final String fileLocationMeanMedianTxt = "/home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/mean_median_report.txt";

        try {
            switch (pilihan) {
                case 1:
                    File file = new File(fileLocationModusTxt);
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(generateModusTxt());
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    System.out.println(pembatas +
                            "\nAplikasi Pengolah Nilai Siswa\n" +
                            pembatas +
                            "\nFile telah di generate di /home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/modus_report.txt\n" +
                            "Silahkan cek\n" +
                            "0. Exit\n" +
                            "1. Kembali ke menu utama\n"
                    );
                    break;
                case 2:
                    File file2 = new File(fileLocationMeanMedianTxt);
                    FileWriter fileWriter2 = new FileWriter(file2);
                    BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                    bufferedWriter2.write(generateMeanMedianTxt());
                    bufferedWriter2.flush();
                    bufferedWriter2.close();

                    System.out.println(pembatas +
                            "\nAplikasi Pengolah Nilai Siswa\n" +
                            pembatas +
                            "\nFile telah di generate di /home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/mean_median_report.txt\n" +
                            "Silahkan cek\n" +
                            "0. Exit\n" +
                            "1. Kembali ke menu utama\n"
                    );
                    break;
                case 3:
                    // Ada 2 file, generate dua kali
                    File file3 = new File(fileLocationModusTxt);
                    FileWriter fileWriter3 = new FileWriter(file3);
                    BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);
                    bufferedWriter3.write(generateModusTxt());
                    bufferedWriter3.flush();
                    bufferedWriter3.close();

                    File file4 = new File(fileLocationMeanMedianTxt);
                    FileWriter fileWriter4 = new FileWriter(file4);
                    BufferedWriter bufferedWriter4 = new BufferedWriter(fileWriter4);
                    bufferedWriter4.write(generateMeanMedianTxt());
                    bufferedWriter4.flush();
                    bufferedWriter4.close();

                    System.out.println(pembatas +
                            "\nAplikasi Pengolah Nilai Siswa\n" +
                            pembatas +
                            "\nFile telah di generate di /home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/modus_report.txt dan di /home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/mean_median_report.txt\n" +
                            "Silahkan cek\n" +
                            "0. Exit\n" +
                            "1. Kembali ke menu utama\n"
                    );
                    break;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String generateMeanMedianTxt() {
        StringBuilder stringBuilder = new StringBuilder("Berikut Hasil Pengolahan Nilai: \n\n");
        ArrayList<Integer> dataNilaiSemuaKelas = new ArrayList<>();

        // Menampilkan mean, median, modus untuk masing-masing kelas
        mapDataKelas.forEach((key, value) -> {
            stringBuilder.append("Kelas: ")
                    .append(key)
                    .append("\nBerikut Hasil Sebaran Data Nilai")
                    .append("\nMean: ")
                    .append(calculatorMean.calculate(value.getDataNilai()))
                    .append("\nMedian: ")
                    .append(calculatorMedian.calculate(value.getDataNilai()))
                    .append("\nModus: ")
                    .append(calculatorPopularScore.calculate(value.getDataNilai()))
                    .append("\n\n");
            for (int item : value.getDataNilai()) {
                dataNilaiSemuaKelas.add(item);
            }
        });

        // Menampillkan mean, median, modus untuk semua kelas
        Collections.sort(dataNilaiSemuaKelas);
        int[] dataNilai = new int[dataNilaiSemuaKelas.size()];
        Iterator<Integer> iterator = dataNilaiSemuaKelas.iterator();
        for (int i = 0; i < dataNilai.length; i++) {
            dataNilai[i] = iterator.next();
        }
        stringBuilder.append("Kelas: (semua kelas)")
                .append("\nBerikut Hasil Sebaran Data Nilai")
                .append("\nMean: ")
                .append(calculatorMean.calculate(dataNilai))
                .append("\nMedian: ")
                .append(calculatorMedian.calculate(dataNilai))
                .append("\nModus: ")
                .append(calculatorPopularScore.calculate(dataNilai))
                .append("\n\n");

        return stringBuilder.toString();
    }

    /**
     * Fungsi untuk menuliskan isi dari suatu teks file txt hasil generate fungsi fileWriter()
     * Khusus untuk menuliskan hasil modus dari data nilai
     * @return String yang berisi tulisan/teks yang akan dituliskan ke dalam file
     */
    private String generateModusTxt() {
        StringBuilder stringBuilder = new StringBuilder("Berikut Hasil Pengolahan Nilai: \n");
        ArrayList<Integer> dataNilaiSemuaKelas = new ArrayList<>();

        // Menampilkan nilai modus untuk masing-masing kelas
        mapDataKelas.forEach((key, value) -> {
            stringBuilder.append("Kelas: ")
                    .append(key)
                    .append("\n")
                    .append(calculatorScoreWithFrequencies.calculate(value.getDataNilai()))
                    .append("Modus: ")
                    .append(calculatorPopularScore.calculate(value.getDataNilai()))
                    .append("\n\n");
            for (int item : value.getDataNilai()) {
                dataNilaiSemuaKelas.add(item);
            }
        });

        // Menampilkan modus untuk semua kelas
        int[] dataNilai = new int[dataNilaiSemuaKelas.size()];
        Iterator<Integer> iterator = dataNilaiSemuaKelas.iterator();
        for (int i = 0; i < dataNilai.length; i++) {
            dataNilai[i] = iterator.next();
        }
        stringBuilder.append("Kelas: (semua kelas)")
                .append("\n")
                .append(calculatorScoreWithFrequencies.calculate(dataNilai))
                .append("Modus: ")
                .append(calculatorPopularScore.calculate(dataNilai))
                .append("\n\n");
        return stringBuilder.toString();
    }
}
