package ru.itis;

import java.util.ArrayList;

public class IntOperations {
    //ФАКТОРИАЛ
    public static String fact(String n) {
        if (!isNum(n)) {
            System.err.println("Вводить надо числj");
            return null;
        }
        String fact = n;
        while (!n.equals("1")) {
            String cross = minus(n, "1");
            fact = multiplication(fact, cross);
            n = cross;
        }
        return fact;
    }

    //СТЕПЕНЬ
    public static String pow (String n, String degree){
        if (!isNum(n) && !isNum(degree)) {
            System.err.println("Вводить надо числа");
            return null;
        }
        String cross = "";
        String pow = "1";

        while (!degree.equals(cross)){
            pow = multiplication(pow, n);
            cross = totalSum(cross, "1");
        }
        return pow;
    }

    //ВЫЧИТАНИЕ 2 ЧИСЕЛ
    public static String minus(String plus, String minus) {
        ArrayList<Integer> difference = ALOperation.stringToAL(plus);
        char[] min = minus.toCharArray();
        int count;
        int cross;
        int minuse = 0;

        if (!isNum(plus) && !isNum(minus)) {
            System.err.println("Вводить надо числа");
            return null;
        }

        for (count = 1; count <= min.length; count++) {
            cross = difference.get(difference.size() - count) - min[min.length - count] + '0';
            if (minuse > 0) {
                cross -= minuse;
                minuse = 0;
            }

            if (cross < 0) {
                difference.set(difference.size() - count, (cross + 10) % 10);
                minuse++;
            } else difference.set(difference.size() - count, cross);
        }
        while (minuse != 0) {
            cross = difference.get(difference.size() - count) - minuse;
            minuse = 0;
            if (cross < 0) {
                difference.set(difference.size() - count, -cross % 10);
                minuse = -cross / 10;
            } else difference.set(difference.size() - count, cross);
        }
        try {
            while (difference.get(0).equals(0)) {
                difference.remove(0);
            }
        } catch (IndexOutOfBoundsException exception) {
            difference.add(0);
        }
        return ALOperation.ALtoString(difference);
    }

    //ПРОИЗВЕДЕНИЕ
    public static String multiplication(String term1, String term2) {
        int length;
        String multiplication = "0";
        ArrayList<Integer> crossing;
        String cross;
        String termBig;
        String termSmall;

        if (!isNum(term1) || !isNum(term2)) {
            System.err.println("Вводить надо числа");
            return null;
        }

        if (term1.length() > term2.length()) {
            length = term2.length();
            termBig = term1;
            termSmall = term2;
        } else {
            length = term1.length();
            termBig = term2;
            termSmall = term1;
        }

        char[] term = termSmall.toCharArray();
        for (int count = 0; count < length; count++) {
            cross = easyMultiplication(termBig, String.valueOf(term[term.length - count - 1]));
            crossing = ALOperation.stringToAL(cross);
            for (int inCount = 0; inCount < count; inCount++) {
                crossing.add(0);
            }
            multiplication = totalSum(multiplication, ALOperation.ALtoString(crossing));
        }

        return multiplication;
    }

    //УМНОЖЕНИЕ НА 1 ЦИФРУ
    private static String easyMultiplication(String term1, String term2) {
        ArrayList<Integer> easyMultiplication;
        int c;
        int cross;
        int plus = 0;
        String toCheck;
        if (term1.length() > term2.length()) {
            easyMultiplication = ALOperation.stringToAL(term1);
            toCheck = term2;
        } else {
            easyMultiplication = ALOperation.stringToAL(term2);
            toCheck = term1;
        }

        if (!isNum(term1) && !isNum(term2)) {
            System.err.println("Вводить надо числа");
            return null;
        }

        if (toCheck.length() > 1) {
            System.err.println("Где - то косяк");
            return null;
        } else c = toCheck.charAt(0) - '0';

        if (c == '0') return "0";

        for (int count = easyMultiplication.size() - 1; count >= 0; count--) {
            cross = easyMultiplication.get(count) * c;
            if (plus > 0) {
                cross += plus;
                plus = 0;
            }

            if (cross > 9) {
                plus = cross / 10;
                easyMultiplication.set(count, cross % 10);
            } else easyMultiplication.set(count, cross);
        }

        if (plus > 0) {
            easyMultiplication = ALOperation.rebuildAL(easyMultiplication);
            easyMultiplication.add(plus);
            easyMultiplication = ALOperation.rebuildAL(easyMultiplication);
        }
        return ALOperation.ALtoString(easyMultiplication);
    }

    //ПРОВЕРКА ЧИСЛА ЛИ
    private static boolean isNum(String s) {
        char[] num = s.toCharArray();
        for (int count = 0; count < num.length; count++) {
            if ('0' <= num[count] && '9' >= num[count]) continue;
            else return false;
        }
        return true;
    }

    //СУММА 2Х ЧИСЕЛ
    public static String totalSum(String term1, String term2) {
        int cross;
        int plus = 0;
        int count;
        char[] min;
        int length;
        ArrayList<Integer> total;

        if (!isNum(term1) && !isNum(term2)) {
            System.err.println("Вводить надо числа");
            return null;
        }

        if (term1.length() > term2.length()) {
            total = ALOperation.stringToAL(term1);
            length = term2.length();
            min = term2.toCharArray();
        } else {
            total = ALOperation.stringToAL(term2);
            length = term1.length();
            min = term1.toCharArray();
        }

        for (count = 1; count <= length; count++) {
            cross = total.get(total.size() - count) + min[length - count] - '0';
            if (plus > 0) {
                cross += plus;
                plus = 0;
            }

            if (cross > 9) {
                total.set(total.size() - count, cross % 10);
                plus = cross / 10;
            } else total.set(total.size() - count, cross);
        }

        while (plus != 0 && count <= total.size()) {
            total.set(total.size() - count, total.get(total.size() - count) + plus);
            if (total.get(total.size() - count) > 9) {
                plus = total.get(total.size() - count) / 10;
                total.set(count, total.get(total.size() - count) % 10);
                count++;
            } else plus = 0;
        }

        if (plus != 0) {
            total = ALOperation.rebuildAL(total);
            total.add(1);
            total = ALOperation.rebuildAL(total);
        }
        return ALOperation.ALtoString(total);
    }
}