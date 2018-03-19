package ru.itis;

import ru.itis.search.algoritm.algoritmLee.as.wave.StaticWaveLee;

public class Main {

    public static void main(String[] args) {
        int [][] firstTry = {
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, -2},
                {0, -2, -2, -2, 0},
                {0, -2, -1, -2, 0}
        };
        System.out.println(StaticWaveLee.founder(firstTry));
    }
}
