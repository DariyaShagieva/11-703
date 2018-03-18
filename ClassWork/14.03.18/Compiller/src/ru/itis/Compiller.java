package ru.itis;

import ru.itis.Variable.Variable;
import ru.itis.Variable.VariableBuilder;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

/**
 * Compiller
 * <p>
 * 16 03 2018
 *
 * @author Nita
 */

//"x1:=123;x2:=x1+x3"
public class Compiller {
    public static void analise(String input) {
        char[] inputAsChar = input.toCharArray();
        int length = inputAsChar.length;
        int state = 0;
        try {
            for (int c = 0; c < length; c++) {
                switch (state) {
                    case 0:
                        //проверка комбинации Буква + цифра + :=
                        if (Character.isLetter(inputAsChar[c++]) && Character.isDigit(inputAsChar[c++])
                                && inputAsChar[c++] == ':' && inputAsChar[c] == '=') {
                            state++;
                            break;
                        } else {
                            state = 2;
                            break;
                        }


                    case 1:
                        //Проверка на число/отрицательное число
                        if (Character.isDigit(inputAsChar[c])  || (inputAsChar[c] == '-')) {
                            //если число - пропускаем все цифры, чтобы сократить количество итераций
                            while (Character.isDigit(inputAsChar[c + 1])) {
                                c++;
                            }
                            break;
                        }

                        //Проверка на сложение с переменной типа Буква + цифра
                        if (Character.isLetter(inputAsChar[c]) && Character.isDigit(inputAsChar[c + 1])) {
                            c++;
                            break;
                            }

                        //Проверка на наличие знаков +-*/ и чтобы данный знак был лишь 1 подряд
                        if ("+-/*".indexOf(inputAsChar[c]) >= 0 && "+-/*".indexOf(inputAsChar[c + 1]) < 0) {
                            break;
                        }

                        //Окончание значения переменной
                        if (inputAsChar[c] == ';') {
                            //Если больше нет переменных, все остальные факторы проверены - завершаем
                            //И выводим статус последовательности
                            if (c == length - 1) {
                                state = -1;
                                System.out.println("Все хорошо С=");
                                break;
                            }
                            //иначе - начинаем все с начала
                            else {
                                state = 0;
                                break;
                            }
                        }

                        //если что - то не подходит обрываемым условиям последовательности - ловим ошибку
                        state = 2;
                        break;

                    case 2:
                        throw new InputMismatchException("ошибка");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //алгоритм прописан так, что в случае ошибки, связанной с выходом за пределы массива (например, "x1:" будет автоматически выводиться требуемая ошибка)
            throw new InputMismatchException("ошибка");
        }
        //С другой стороны, в случае если мы не перевели state в - 1 (ситуация - нет ";") - он автоматически выбрасывает ошибку
        if (state != -1) {
            throw new InputMismatchException("ошибка");
        }
    }

    public static List<Variable> process(String input) {
        return VariableBuilder.createList(input);
    }

}




