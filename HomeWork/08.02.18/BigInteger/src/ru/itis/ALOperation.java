package ru.itis;

import java.util.ArrayList;

public class ALOperation {
    //ЗАПИСЬ В ОБРАТНОМ ПОРЯДКЕ
    public static ArrayList<Integer> rebuildAL(ArrayList<Integer> reTotal) {
        int length = reTotal.size();
        ArrayList<Integer> total = new ArrayList<>();
        for (int count = 0; count < length; count++) {
            total.add(reTotal.get(length - count - 1));
        }
        return total;
    }

    //ПРЕОБРАЗОВАНИЕ ArrayList В String
    public static String ALtoString(ArrayList<Integer> total) {
        String totalString = "";
        for (int x : total) {
            totalString += x;
        }
        return totalString;
    }

    //ПРЕОБРАЗОВАНИЕ String в ArrayList
    public static ArrayList<Integer> stringToAL(String s) {
        ArrayList<Integer> num = new ArrayList<>();
        char[] numOnChar = s.toCharArray();
        for (char c : numOnChar) {
            num.add(c - '0');
        }
        return num;
    }
}
