package ru.itis.Variable;

import ru.itis.Compirer;

import java.util.LinkedList;

/**
 * Compirer
 * <p>
 * 18 03 2018
 *
 * @author Nita
 */
public class VariableBuilder {
    private static LinkedList<Variable> variables = new LinkedList<>();

    public static LinkedList<Variable> createList(String input) {
        Compirer.analise(input);
        String[] vars = input.split(";");
        int cross;
        boolean founded = false;
        for (String var : vars) {
            //Если переменная число/отрицательное число - все просто
            //Переводим его значение
            Variable variable = new Variable(var);
            if (isNum(var)) {
                variable.setAnswer(Integer.parseInt(var.split(":=")[1]));
                variables.add(variable);
            }
            //Если не число (или не только число). Ну или сумма/произведение/частное/разность
            else {
                String polinom = var.split(":=")[1].split(";")[0];
                char[] polinomAsChar = polinom.toCharArray();
                char signOfValue = '+';
                int p = 0;
                if (polinomAsChar[0] == '-') {
                    signOfValue = '-';
                    p++;
                }

                int currentNum = 0;
                for (int c = p; c < polinomAsChar.length; c++) {
                    StringBuilder currentVar = new StringBuilder();
                    while (c < polinomAsChar.length && polinomAsChar[c] != '-' && polinomAsChar[c] != '+' && polinomAsChar[c] != '*' && polinomAsChar[c] != '/') {
                        currentVar.append(polinomAsChar[c]);
                        c++;
                    }

                    if (isNumVar(String.valueOf(currentVar))) {
                        cross = Integer.parseInt(String.valueOf(currentVar));
                    } else {
                        int currentNums;
                        if ((currentNums = exists(String.valueOf(currentVar))) >= 0) {
                            cross = variables.get(currentNums).getAnswer();
                        } else {
                            throw new ExceptionInInitializerError();
                        }
                    }

                    switch (signOfValue) {
                        case '-':
                            currentNum -= cross;
                            break;
                        case '*':
                            currentNum *= cross;
                            break;
                        case '+':
                            currentNum += cross;
                            break;
                        case '/':
                            currentNum /= cross;
                            break;
                    }
                    if (c < polinomAsChar.length) {
                        signOfValue = polinomAsChar[c];
                    }
                }
                variable.setAnswer(currentNum);
                variables.add(variable);
                currentNum = 0;
            }
        }
        return variables;
    }

    private static boolean isNumVar(String var) {
        char[] varValue = var.toCharArray();
        boolean equals = true;
        for (char current : varValue) {
            if (!Character.isDigit(current)) {
                equals = false;
                break;
            }
        }
        return equals;
    }

    private static boolean isNum(String var) {
        char[] varValue = var.split(":=")[1].toCharArray();
        boolean equals = true;
        int c = 0;
        if (varValue[c] == '-') {
            c++;
        }
        for (int count = c; count < varValue.length; count++) {
            if (!Character.isDigit(varValue[count])) {
                equals = false;
                break;
            }
        }
        return equals;
    }

    private static int exists(String varName) {
        int c = 0;
        for (Variable var : variables) {
            if (var.getName().equals(varName)) return c;
            c++;
        }

        return -1;
    }
}
