package ru.itis;

import ru.itis.Variable.Variable;
import ru.itis.Variable.VariableBuilder;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList <Variable> current = VariableBuilder.createList("x1:=-7;x3:=x1+9;x2:=x3;x4:=x2+x3+7*x1;x9=x10");
        for (Variable var: current){
            System.out.println(var.getName() + " " + var.getAnswer() + " " + var.getVar());
        }
        int c = 0;
    }


}
