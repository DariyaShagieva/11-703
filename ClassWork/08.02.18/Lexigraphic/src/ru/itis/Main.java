package ru.itis;

public class Main {

    public static void main(String[] args) {
        System.out.println(lexigraphicAnalise("Anitas", "Anita"));
    }

    public static int lexigraphicAnalise (String s1, String s2){
        int length;
        if (s1.length() > s2.length()) length = s2.length();
        else length = s1.length();

        if (s1.equals(s2)) return 0;

        for (int count = 0; count < length; length++){
            if (s1.charAt(count) > s2.charAt(count)) return 1;
        }

        if (s1.length() > s2.length()) return 1;

        return -1;
    }
}
