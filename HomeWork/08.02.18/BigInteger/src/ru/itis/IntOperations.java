package ru.itis;

import java.util.ArrayList;

public class IntOperations {
    //ФАКТОРИАЛ
    public static String fact(String n) {
        String fact = n;
        while (!n.equals("1")) {
            fact = multiplication(fact, minus(n, "1"));
            n = minus(n, "1");
        }
        return fact;
    }

    //ВЫЧИТАНИЕ 2 ЧИСЕЛ
    public static String minus(String plus, String minus) {
        ArrayList<Integer> difference = stringToAL(plus);
        char[] minue = minus.toCharArray();
        int count;
        int cross;
        int minuse = 0;
        for (count = 1; count <= minue.length; count++) {
            cross = difference.get(difference.size() - count) - minue[minue.length - count] + '0';
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
        return ALtoString(difference);
    }

    //ПРОИЗВЕДЕНИЕ
    public static String multiplication(String term1, String term2) {
        int length;
        String multiplication = "0";
        ArrayList<Integer> crossing;
        String cross;
        String termBig;
        String termSmall;

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
            crossing = stringToAL(cross);
            for (int inCount = 0; inCount < count; inCount++) {
                crossing.add(0);
            }
            multiplication = totalSum(multiplication, ALtoString(crossing));

        }

        return multiplication;
    }

    //УМНОЖЕНИЕ НА 1 ЦИФРУ
    private static String easyMultiplication(String term1, String term2) {
        ArrayList<Integer> easyMultiplication = makeBiggestAsAL(term1, term2);
        int c;
        int cross;
        int plus = 0;
        String toCheck;
        if (term1.length() > term2.length()) {
            toCheck = term2;
        } else toCheck = term1;
        if (toCheck.length() > 1) {
            System.err.println("Где - то косяк");
            return null;
        } else c = toCheck.charAt(0) - '0';

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
            easyMultiplication = rebuildAL(easyMultiplication);
            easyMultiplication.add(plus);
            easyMultiplication = rebuildAL(easyMultiplication);
        }
        return ALtoString(easyMultiplication);
    }

    //СУММА 2Х ЧИСЕЛ
    public static String totalSum(String term1, String term2) {
        ArrayList<Integer> total = makeBiggestAsAL(term1, term2);
        int cross;
        int plus = 0;
        int count;
        char[] min;
        int length;

        if (term1.length() > term2.length()) {
            length = term2.length();
            min = term2.toCharArray();
        } else {
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
            total.add(1);
        }

        return ALtoString(total);
    }

    //ИЗ БОЛЬШЕГО МАССИВА - ArrayList
    private static ArrayList<Integer> makeBiggestAsAL(String s1, String s2) {
        char[] cross;
        int length;
        ArrayList<Integer> biggestAL = new ArrayList();
        if (s1.length() > s2.length()) {
            cross = s1.toCharArray();
            length = s1.length();
        } else {
            cross = s2.toCharArray();
            length = s2.length();
        }

        for (int count = 0; count < length; count++) {
            biggestAL.add(cross[count] - '0');
        }
        return biggestAL;
    }

    //ЗАПИСЬ В ОБРАТНОМ ПОРЯДКЕ
    private static ArrayList<Integer> rebuildAL(ArrayList<Integer> reTotal) {
        int length = reTotal.size();
        ArrayList<Integer> total = new ArrayList<>();
        for (int count = 0; count < length; count++) {
            total.add(reTotal.get(length - count - 1));
        }
        return total;
    }

    //ПРЕОБРАЗОВАНИЕ ArrayList В String
    private static String ALtoString(ArrayList<Integer> total) {
        String totalString = "";
        for (int x : total) {
            totalString += x;
        }
        return totalString;
    }

    //ПРЕОБРАЗОВАНИЕ String в ArrayList
    private static ArrayList<Integer> stringToAL(String s) {
        ArrayList<Integer> num = new ArrayList<>();
        char[] numOnChar = s.toCharArray();
        for (char c : numOnChar) {
            num.add(c - '0');
        }
        return num;
    }
}

