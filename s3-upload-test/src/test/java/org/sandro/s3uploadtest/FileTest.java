package org.sandro.s3uploadtest;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTest {
    @Test
    void 파일분할() throws Exception {
        int CHUNK_SIZE = 5 * 1024 * 1024; // 원하는 분할 크기

        String 파일_경로 = "src/test/resources/image.jpg";
        File file = new File(파일_경로);

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[CHUNK_SIZE];
            int bytesRead;
            int partNumber = 1;

            while ((bytesRead = fis.read(buffer)) != -1) {
                String 아웃풋_파일_경로 = "src/test/resources/part_%03d";
                String outputFilePath = String.format(아웃풋_파일_경로, partNumber++);
                try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                    fos.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("파일 분할 완료.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
