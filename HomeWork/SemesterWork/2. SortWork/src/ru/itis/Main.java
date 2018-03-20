package ru.itis;

import ru.itis.randomArraysCreator.IntArraysFileReader;
import ru.itis.randomArraysCreator.IntArraysMaxFileGenerator;
import ru.itis.sorts.ShellSort.StaticShellSort;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IntArraysMaxFileGenerator.writer("ArraysInfo");
        int[][] toSort = IntArraysFileReader.getFromFile("ArraysInfo");
        for (int[] sort : toSort) {
            sort = StaticShellSort.toSort(sort);
            System.out.println(StaticShellSort.getNumOfAction() + " " + StaticShellSort.getTimeOfAction() + " " + sort.length);
        }
        Scanner in = new Scanner(System.in);
    }

    public static void arrToString (int arr []){
        for (int c : arr){
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
