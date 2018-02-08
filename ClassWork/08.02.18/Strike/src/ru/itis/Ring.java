package ru.itis;

public class Ring {
    private int radius;
    private int score;

    //	Чистая аналогия с Shoot  - смотри там
    public Ring(int radius, int score) {
        this.radius = radius;
        this.score = score;
    }

    public int getRadius() {
        return radius;
    }

    public int getScore() {
        return score;
    }
}
