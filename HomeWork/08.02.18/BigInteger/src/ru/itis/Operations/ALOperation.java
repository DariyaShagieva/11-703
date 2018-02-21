package ru.itis.Operations;

import java.util.ArrayList;

public class ALOperation {
    //ПРЕОБРАЗОВАНИЕ ArrayList В String
    protected static String ALtoString(ArrayList<Integer> total) {
        String totalString = "";
        for (int x : total) {
            totalString += x;
        }
        return totalString;
    }

    //ПРЕОБРАЗОВАНИЕ String в ArrayList
    protected static ArrayList<Integer> stringToAL(String s) {
        ArrayList<Integer> num = new ArrayList<>();
        char[] numOnChar = s.toCharArray();
        for (char c : numOnChar) {
            num.add(c - '0');
        }
        return num;
    }
}
