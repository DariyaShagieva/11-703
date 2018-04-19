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

        private PointAndSign(double point, char sign) {
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
        Variable variable;
        //x1  -7
        //x3  x1+9
        //x2  x3
        //x4  x2*x2+x3+7/x1
        for (String var : vars) {
            //Если переменная число/отрицательное число - все просто
            //Переводим его значение
            double value = 0;
            String [] polinome = var.split(":=");
            if (isNumValue(polinome[1])){
                variable = new Variable(polinome[0], Integer.parseInt(polinome[1]));
            } else {
                //Если не число (или не только число). Ну или сумма/произведение/частное/разность
                char[] polinomeAsChar = polinome[1].toCharArray();
                char signOfValue = '+';
                int p = 0;
                if (polinomeAsChar[0] == '-') {
                    signOfValue = '-';
                    p++;
                }

                LinkedList <PointAndSign> pointAndSigns = new LinkedList<>();
                for (int c = p; c < polinomeAsChar.length; c++) {
                    PointAndSign currentPaS;
                    StringBuilder currentVar = new StringBuilder();
                    //собираем элементы одночлена по одному
                    while (c < polinomeAsChar.length && polinomeAsChar[c] != '-' && polinomeAsChar[c] != '+'
                            && polinomeAsChar[c] != '*' && polinomeAsChar[c] != '/') {
                        currentVar.append(polinomeAsChar[c]);
                        c++;
                    }

                    //если одночлен число - просто сразу его добавляем в набор
                    if (isNumValue(String.valueOf(currentVar))) {
                        cross = Integer.parseInt(String.valueOf(currentVar));
                    } else {
                        int currentNum;
                        //иначе ищем его среди существующих значений
                        if ((currentNum = exists(String.valueOf(currentVar))) >= 0) {
                            cross = variables.get(currentNum).getValue();
                        } else {
                            //если не находим - ошибка
                            throw new ExceptionInInitializerError();
                        }
                    }

                    //создаем и добавляем новый элемент многочлена
                    currentPaS = new PointAndSign(cross, signOfValue);
                    pointAndSigns.add(currentPaS);

                    //записываем следующий знак перед элементом многочлена
                    if (c < polinomeAsChar.length) {
                        signOfValue = polinomeAsChar[c];
                    }
                }

                boolean multiplication;
                PointAndSign rebuild;
                //ищем и убираем произведения
                do{
                    multiplication = false;
                    for (int c = 0; c < pointAndSigns.size(); c++){
                        if (pointAndSigns.get(c).sign == '*' || pointAndSigns.get(c).sign == '/'){
                            multiplication = true;
                            switch (pointAndSigns.get(c).sign){
                                case '*':
                                    value = pointAndSigns.get(c-1).point * pointAndSigns.get(c).point;
                                    break;
                                case '/':
                                    value = pointAndSigns.get(c-1).point / pointAndSigns.get(c).point;
                                    break;
                            }
                            rebuild = new PointAndSign(value, pointAndSigns.get(c-1).sign);
                            pointAndSigns.set(c - 1, rebuild);
                            pointAndSigns.remove(c);
                        }
                    }
                    //до тех пор пока находим * или /
                } while (multiplication);

                value = 0;
                for (int c = 0; c < pointAndSigns.size(); c++){
                    switch (pointAndSigns.get(c).sign){
                        case '+':
                            value += pointAndSigns.get(c).point;
                            break;
                        case '-':
                            value -= pointAndSigns.get(c).point;
                            break;
                    }
                }
                variable = new Variable(polinome[0], value);
            }

            variables.add(variable);
        }
        return variables;
    }

    private static boolean isNumValue(String var) {
        char[] varValue = var.toCharArray();
        int c = 0;
        if (varValue[0] == '-'){
            c++;
        }
        boolean equals = true;
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