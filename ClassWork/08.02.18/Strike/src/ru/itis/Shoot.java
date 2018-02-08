package ru.itis;

public class Shoot {
    private int x;
    private int y;
    private double length;

    public Shoot(int x, int y) {
        this.x = x;
        this.y = y;
        length = lengthFounder(x, y);
    }

    public double getLength() {
        return length;
    }

    public double lengthFounder(int x, int y) {
        return (Math.sqrt(x * x + y * y));
    }
}
