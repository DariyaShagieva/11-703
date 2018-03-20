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
    private static class PointAndSign {
        double point;
        char sign;

        public PointAndSign(double point, char sign) {
            this.point = point;
            this.sign = sign;
        }

        public double getPoint() {
            return point;
        }

        public char getSign() {
            return sign;
        }
    }

    private static LinkedList<Variable> variables = new LinkedList<>();

    public static LinkedList<Variable> createList(String input) {
        Compirer.analise(input);
        String[] vars = input.split(";");
        double cross;
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

                LinkedList <PointAndSign> pointAndSigns = new LinkedList<>();
                double currentNum = 0;
                for (int c = p; c < polinomAsChar.length; c++) {
                    PointAndSign currentPaS;
                    StringBuilder currentVar = new StringBuilder();
                    //собираем элементы одночлена по одному
                    while (c < polinomAsChar.length && polinomAsChar[c] != '-' && polinomAsChar[c] != '+' && polinomAsChar[c] != '*' && polinomAsChar[c] != '/') {
                        currentVar.append(polinomAsChar[c]);
                        c++;
                    }

                    //если одночлен число - просто сразу его добавляем в набор
                    if (isNumVar(String.valueOf(currentVar))) {
                        cross = Integer.parseInt(String.valueOf(currentVar));
                    } else {
                        int currentNums;
                        //иначе ищем его среди существующих значений
                        if ((currentNums = exists(String.valueOf(currentVar))) >= 0) {
                            cross = variables.get(currentNums).getAnswer();
                        } else {
                            //если не находим - ошибка
                            throw new ExceptionInInitializerError();
                        }
                    }

                    //создаем и добавляем новый элемент многочлена
                    currentPaS = new PointAndSign(cross, signOfValue);
                    pointAndSigns.add(currentPaS);

                    //записываем следующий знак перед элементом многочлена
                    if (c < polinomAsChar.length) {
                        signOfValue = polinomAsChar[c];
                    }
                }

                boolean multiplication;
                char currentSign;
                PointAndSign rebuild;
                double currentSet = 0;
                //ищем и убираем произведения
                do{
                    multiplication = false;
                    for (int c = 0; c < pointAndSigns.size(); c++){
                        if (pointAndSigns.get(c).sign == '*' || pointAndSigns.get(c).sign == '/'){
                            multiplication = true;
                            switch (pointAndSigns.get(c).sign){
                                case '*':
                                    currentSet = pointAndSigns.get(c-1).point * pointAndSigns.get(c).point;
                                    break;
                                case '/':
                                    currentSet = pointAndSigns.get(c-1).point / pointAndSigns.get(c).point;
                                    break;
                            }
                            currentSign = pointAndSigns.get(c-1).sign;
                            rebuild = new PointAndSign(currentSet, currentSign);
                            pointAndSigns.set(c - 1, rebuild);
                            pointAndSigns.remove(c);
                        }
                    }
                    //до тех пор пока находим * или /
                } while (multiplication);

                for (int c = 0; c < pointAndSigns.size(); c++){
                    switch (pointAndSigns.get(c).sign){
                        case '+':
                            currentNum += pointAndSigns.get(c).point;
                            break;
                        case '-':
                            currentNum -= pointAndSigns.get(c).point;
                            break;
                    }
                }

                variable.setAnswer(currentNum);
                variables.add(variable);
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