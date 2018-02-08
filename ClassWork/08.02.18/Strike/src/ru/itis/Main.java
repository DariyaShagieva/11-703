package ru.itis;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ScoreFounder found = new ScoreFounder();
		//вызываем полученный метод, чтобы увидеть результат
        System.out.println(found.finalScore());
    }
}
