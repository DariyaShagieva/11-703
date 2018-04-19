package ru.itis.Variable;

/**
 * Compirer
 * <p>
 * 16 03 2018
 *
 * @author Nita
 */
public class Variable {
    private String name;
    private double value;

    public Variable(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        Variable var = (Variable) obj;
        if (var.getName().equals(name) && var.getValue() == value) return true;
        else return false;
    }
}
