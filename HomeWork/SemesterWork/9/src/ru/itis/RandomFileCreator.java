package ru.itis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class RandomFileCreator {

    private int numOf;
    private String fileName;
    private ArrayList <String> notExist;

    public RandomFileCreator(int numOf, String fileName){
        this.numOf = numOf;
        this.fileName = fileName;
    }

    private void numGenerator (){
        notExist = new ArrayList();
        Random r = new Random();
        boolean hasEquals = false;
        for (int count = 0; count < numOf; count++){
            String toAdd = "";
            for (int inCount = 0; inCount < 4; inCount++){
                toAdd += (- 10 + r.nextInt(20)) + " ";
            }
            for (String current : notExist){
                if (toAdd.equals(current)){
                    count--;
                    hasEquals = true;
                    break;
                }
            }
            if (!hasEquals) notExist.add(toAdd);
        }
    }

    public void writeToFile () throws IOException {
        numGenerator();
        BufferedWriter file = new BufferedWriter(new FileWriter(fileName));
        for (String current : notExist){
            file.write(current);
            file.newLine();
        }
        file.close();
    }
}
