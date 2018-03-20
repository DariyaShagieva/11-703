package ru.itis.randomArraysCreator;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * 2. SortWork
 *
 * 13 03 2018
 *
 * @author Nita
 */
public class IntArraysFileReader {
    public static int [][] getFromFile(String fileName){
        int[][] fileArray;
        try {
            Scanner file = new Scanner(new File(fileName));
            Random random = new Random();
            String [] current;
            int neededArrays = random.nextInt(50) + 50;
            fileArray = new int[neededArrays][];
            int lineLength;
            for (int count = 0; count < neededArrays; count++ ){
                lineLength = random.nextInt(4900) + 100;
                current = file.nextLine().split(" ");
                fileArray[count] = new int [lineLength];
                for (int inCount = 0; inCount < lineLength; inCount++){
                    fileArray[count][inCount] = Integer.parseInt(current[inCount]);
                }
            }
            file.close();
        } catch (IOException e){
            System.err.println("Опять криво");
            fileArray = null;
        }
        return fileArray;
    }
}
