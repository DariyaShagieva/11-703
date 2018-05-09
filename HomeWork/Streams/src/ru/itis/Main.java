package ru.itis;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Sort;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Predicate<String> isUpperPredicate =
                string -> Character.isUpperCase(string.charAt(0));
        System.out.println(isUpperPredicate.test("Hello"));

        Function<String,  Integer> stringToInt = o -> o.length();
        LinkedList LL = new LinkedList();
        LL.add("Nita");
        LL.add("Dasha");
        LL.add("Ilyas");
        LL.add("marsel");
        Stream current = LL.stream().filter(isUpperPredicate).map(stringToInt).sorted();

        int i = 0;
    }
}
