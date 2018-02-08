package ru.itis;

public class Shoot {
    private int x;
    private int y;
    private double length;
//	класс хранит в себе только координаты
    public Shoot(int x, int y) {
        this.x = x;
        this.y = y;
		//высчитывается методом класса
        length = lengthFounder(x, y);
    }
	
//	возвращает нам информацию о радиусе выстрела, смотря от центра
    public double getLength() {
        return length;
    }
	
//	ПИФАГОРОВЫ ШТАНЫ ВО ВСЕ СТОРОНЫ РАВНЫ
    public double lengthFounder(int x, int y) {
        return (Math.sqrt(x * x + y * y));
    }
}
