package ru.itis;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();
        System.out.println(searcher(toArr(num)));
        //4127
    }

    public static long fact(int n) {
        if (n == 0) return 1;
        return n * (fact(n - 1));
    }

    public static int[] toArr(String n) {
        char[] num = n.toCharArray();
        int[] numToInt = new int[num.length];
        for (int count = 0; count < num.length; count++) {
            numToInt[count] = num[count] - '0';
        }
        return numToInt;
    }

    public static int searcher(int[] num) {
        int replace = 0;
        for (int count = 0; count < num.length; count++) {
            for (int inCount = count; inCount < num.length; inCount++) {
                if (num[count] < num[inCount]) {
                    replace += fact(num.length - count - 1);
                }
            }
        }
        return replace;
    }
}
