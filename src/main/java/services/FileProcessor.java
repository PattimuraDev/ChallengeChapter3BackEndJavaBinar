package services;

import java.io.IOException;

public interface FileProcessor {
    void readFile(String fileLocation) throws IOException;
    void writeFile(int pilihan);
}
