package ru.itis;

import ru.itis.Operations.IntOperations;

public class Main {

    public static void main(String[] args) {
        //загуглен результат
        System.out.println(IntOperations.multiplication("12345678901234567890", "12345678901234567890").equals("152415787532388367501905199875019052100"));
        System.out.println(IntOperations.totalSum("12345678901234567890", "12345678901234567890").equals("24691357802469135780"));
        System.out.println(IntOperations.multiplication("12345678901234567890", "2"));
        System.out.println(IntOperations.pow("1412412345", "99"));
    }
}
