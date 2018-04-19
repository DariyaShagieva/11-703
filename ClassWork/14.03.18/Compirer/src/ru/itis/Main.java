package ru.itis;

import ru.itis.Variable.Variable;
import ru.itis.Variable.VariableBuilder;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList <Variable> current = VariableBuilder.createList("x1:=-7;x3:=x1+9;x2:=x3;x4:=x2*x2+x3+7/x1;");
        for (Variable var: current){
            System.out.println(var.getName() + " := " + "\t" + var.getValue());
        }
    }
}
