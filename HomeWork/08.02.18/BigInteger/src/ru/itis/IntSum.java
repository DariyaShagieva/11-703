package ru.itis;

import java.util.ArrayList;

public class IntSum {

    private static String term1;
    private static String term2;
    private static int length;

//    public IntSum(String term1, String term2) {
//        this.term1 = term1;
//        this.term2 = term2;
//        length = lengthDecision(term1, term2);
//    }

    public static ArrayList<Integer> total(String term1, String term2) {
        ArrayList<Integer> total = makeBiggestAsAL(term1, term2);
        int cross = 0;
        int plus = 0;
        int count;
        char [] min;

        if (term1.length() > term2.length()){
            length = term2.length();
            min = term2.toCharArray();
        }
        else {
            length = term1.length();
            min = term1.toCharArray();
        }

        for (count = 1; count <= length; count++) {
            cross = total.get(count - 1) + min [length - count] - '0';

            if (plus > 0) {
                cross += plus;
                plus = 0;
            }

            if (cross > 9) {
                total.set(count - 1, cross % 10);
                plus = cross / 10;
            } else total.set(count - 1, cross);
        }

        while (plus != 0 && count <= total.size()) {
            total.set(count - 1, plus);
            if (total.get(count - 1) > 9){
                plus = total.get(count - 1) / 10;
                total.set(count - 1, total.get(count - 1) % 10);
                count++;
            } else plus = 0;
        }

        if(plus != 0){
            total.add(1);
        }

        total = rebuildAL(total);
        return total;
    }

    private static ArrayList <Integer> makeBiggestAsAL (String s1, String s2){
        char[] cross;
        int length;
        ArrayList <Integer> biggestAL = new ArrayList();
        if(s1.length() > s2.length()){
            cross = s1.toCharArray();
            length = s1.length();
        }
        else{
            cross = s2.toCharArray();
            length = s2.length();
        }

        for (int count = 0; count < length; count++){
            biggestAL.add(cross[count] - '0');
        }
        return biggestAL;
    }

    private static ArrayList<Integer> rebuildAL(ArrayList<Integer> reTotal) {
        int length = reTotal.size();
        ArrayList<Integer> total = new ArrayList<>();
        for (int count = 0; count < length; count++) {
            total.add(reTotal.get(length - count - 1));
        }
        return total;
    }

    private int lengthDecision(String s1, String s2) {
        if (s1.length() > s2.length()) length = s2.length();
        else length = s1.length();
        return length;
    }
}
