package ru.itis;

import org.junit.Test;
import ru.itis.Variable.Variable;

import java.util.InputMismatchException;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;

/**
 * Compirer
 * <p>
 * 22 03 2018
 *
 * @author Nita
 */
public class CompirerTest {

    @Test(expected = InputMismatchException.class)
    public void testOnBadData() {
        String input = "A=5";
        Compirer.analise(input);
    }

    @Test
    public void testOnCorrectData() {
        String input = "A1:=5;";
        Compirer.analise(input);
    }

    @Test
    public void testProcess() {
        String input = "x1:=123;x2:=5;x3:=x1+x2;";
        LinkedList <Variable> expected = new LinkedList<>();
        Variable x = new Variable("x1", 123);
        expected.add(x);
        x = new Variable("x2", 5);
        expected.add(x);
        x = new Variable("x3", 128);
        expected.add(x);

        LinkedList<Variable> actual = Compirer.process(input);
        assertEquals(expected, actual);
    }
}
