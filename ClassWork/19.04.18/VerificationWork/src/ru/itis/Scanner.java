package ru.itis;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * VerificationWork
 * <p>
 * 19 04 2018
 *
 * @author Nita
 */
public class Scanner {
    int count;
    FileInputStream inputStream;
    int size;

    public Scanner(String filePath) {
        try {
            size = (int) Files.size(Paths.get(filePath));
            inputStream = new FileInputStream(filePath);
            count = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int nextInt(){
        int returnable = 0;
        try {
            int c;
            byte [] bytes = new byte[size];
            int currentCount = count;
            while ((c = inputStream.read()) != ' ' && count < size && c != -1){
                bytes[count] = (byte) c;
                count++;
            }
            
            int pow = 1;
            for (int cur = count - 1; count - cur <= count - currentCount; cur--){
                if (bytes[cur]!='-'){
                    returnable += (bytes [cur] - '0') * pow;
                    pow *= 10;
                } else returnable*=-1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnable;
    }
}
