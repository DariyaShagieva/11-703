package ru.itis.Variable;

/**
 * Compirer
 * <p>
 * 16 03 2018
 *
 * @author Nita
 */
public class Variable {
    private String var;
    private String name;
    private double answer;

    public Variable(String inputVar) {
        name = inputVar.split(":=")[0];
        var = inputVar.split(":=")[1];
    }

    public String getVar() {
        return var;
    }

    public String getName() {
        return name;
    }

    public double getAnswer(){
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
