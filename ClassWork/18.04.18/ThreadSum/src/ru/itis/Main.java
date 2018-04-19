package ru.itis;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = arrGen(in.nextInt());
        int k = in.nextInt();
        SummatorThread[] summatorThreads = new SummatorThread[k];
        int numOfEl = arr.length /k;
        for (int c = 0; c < k; c++) {
            summatorThreads[c] = new SummatorThread(arr, c, numOfEl);
            summatorThreads[c].start();
        }
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }

        System.out.println(sum);
        int finalSum = 0;

        for (int c = 0; c < k; c++){
            try {
                summatorThreads[c].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int c = numOfEl * k; c < arr.length; c++) {
            finalSum += arr[c];
        }
        for (int c = 0; c < k; c++){
            finalSum += summatorThreads[c].getSum();
        }

        System.out.println(finalSum);
    }

    private static int[] arrGen(int n) {
        Random random = new Random();
        int[] arr = new int[n];
        for (int c = 0; c < n; c++) {
            arr[c] = random.nextInt(1000);
        }
        return arr;
    }
}
