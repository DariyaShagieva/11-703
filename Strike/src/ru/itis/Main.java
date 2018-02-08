package ru.itis;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ScoreFounder found = new ScoreFounder();
        System.out.println(found.finalScore());
    }
}
