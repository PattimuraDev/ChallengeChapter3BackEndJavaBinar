package services;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console implements IConsole{
    @Override
    public void programConsole() {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        int pilihan;
        CsvFileProcessor csvFileProcessor = new CsvFileProcessor();
        final String pembatas = "---------------------------";

        while (running) {
            mainMenu();
            System.out.print("pilihan: ");
            try{
                pilihan = input.nextInt();

                if (pilihan == 0) {
                    System.out.println();
                    running = false;
                } else if(pilihan == 1 || pilihan == 2 || pilihan == 3) {
                    final String resourceFilePath = "/home/dwisatriapatra/IdeaProjects/ChallengeChapter3BackEndJava/src/main/resources/data_sekolah.csv";
                    try{
                        csvFileProcessor.readFile(resourceFilePath);
                        csvFileProcessor.writeFile(pilihan);
                    }catch (IOException ioException){
                        System.out.println(pembatas +
                                "\nAplikasi Pengolah Nilai Siswa\n" +
                                pembatas +
                                "\nFile tidak ditemukan\n" +
                                "0. Exit\n" +
                                "1. Kembali ke menu utama\n"
                        );
                    }catch (NumberFormatException nfe){
                        System.out.println("Terdapat nilai siswa bukan angka pada file csv");
                        System.exit(0);
                    }

                    System.out.print("pilihan: ");
                    pilihan = input.nextInt();
                    switch (pilihan) {
                        case 1:
                            break;
                        case 0:
                            running = false;
                            break;
                    }
                }else{
                    System.out.println("Ulangi input");
                }
            }catch (InputMismatchException ime){
                System.out.println("Ulangi program, input tidak valid");
                running = false;
            }
        }
    }

    private void mainMenu() {
        final String pembatas = "---------------------------";
        System.out.println(pembatas +
                "\nAplikasi Pengolah Nilai Siswa\n" +
                pembatas +
                "\nLetakkan file dengan nama data_sekolah.csv di direktori berikut: /home/dwisatriapatra/IdeaProjects/ChallengeChapter2BackEndJava/src/main/resources\n" +
                "Pilih menu:\n" +
                "1. Generate txt untuk menampilkan modus\n" +
                "2. Generate txt untuk menampilkan nilai rata-rata, median\n" +
                "3. Generate kedua file\n" +
                "0. Exit\n"
        );
    }
}
