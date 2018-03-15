package ru.itis.randomArraysCreator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 2. SortWork
 *
 * 12 03 2018
 *
 * @author Nita
 */
public class IntArraysMaxFileGenerator {

    private static BufferedWriter fileWriter;
    private static int NUM_OF_ARRAYS = 100;
    private static int NUM_OF_ELEMENTS = 5000;

    private static int[] generator() {
        Random random = new Random();
        int[] generated = new int[NUM_OF_ELEMENTS];
        for (int count = 0; count < NUM_OF_ELEMENTS; count++) {
            generated[count] = random.nextInt(500);
        }
        return generated;
    }

    private static int [][] arrGenerator() {
        int[][] arrToWrite = new int[NUM_OF_ARRAYS][NUM_OF_ELEMENTS];
        for (int count = 0; count < NUM_OF_ARRAYS; count++) {
            arrToWrite[count] = generator();
        }
        return arrToWrite;
    }

    public static void writer (String filename){
        try {
            fileWriter = new BufferedWriter(new FileWriter(filename));
            int [][] arrToWrite = arrGenerator();
            for (int [] line : arrToWrite){
                for (int num : line){
                    fileWriter.write(num + " ");
                }
                fileWriter.newLine();
            }
        } catch (IOException exception){
            System.err.println("Ну и криворукая...");
        }
    }
}
