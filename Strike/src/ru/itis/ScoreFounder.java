package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreFounder {

    public int finalScore() throws FileNotFoundException {
        Scanner file = new Scanner(new File("Target"));
        String next;
        int score = 0;

        Ring [] rings = new Ring[Integer.parseInt(file.nextLine())];
        for (int count = 0; count < rings.length; count++){
            next = file.nextLine();
            rings[count] = new Ring(Integer.parseInt(next.split(" ")[0]),
                                    Integer.parseInt(next.split(" ")[1]));
        }

        Shoot [] shoots = new Shoot[Integer.parseInt(file.nextLine())];
        for (int count = 0; count < shoots.length; count++){
            next = file.nextLine();
            shoots[count] = new Shoot(Integer.parseInt(next.split(", ")[0]),
                                        Integer.parseInt(next.split(", ")[1]));
        }

        for (int count = 0; count < shoots.length; count++){
            for (int inCount = 0; inCount < rings.length; inCount++){
                if (shoots[count].getLength() < rings[inCount].getRadius()){
                    score += rings[inCount].getScore();
                    break;
                }
            }
        }
        return score;
    }
}
