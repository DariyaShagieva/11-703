package ru.itis;

import ru.itis.Operations.IntOperations;

public class Main {

    public static void main(String[] args) {
        System.out.println(IntOperations.multiplication("1234567812341234123901121231233413242134213412341234213421241234213412342341141234234567890", "12345123412341213212321334123412341234213467890123451324312467890"));
        System.out.println(IntOperations.totalSum("12345678901234567890", "12345678901234567890"));
        System.out.println(IntOperations.multiplication("12345678901234567890", "2"));
        System.out.println(IntOperations.fact("57"));
        System.out.println(IntOperations.pow("1412412345", "99"));
    }
}
