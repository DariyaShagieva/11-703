package ru.itis;

/**
 * ThreadSum
 * <p>
 * 18 04 2018
 *
 * @author Nita
 */
public class SummatorThread extends Thread {
    private int [] array;
    private int currentPart;
    private int numOfElements;
    private int sum;

    public SummatorThread(int[] array, int currentPart, int numOfElements) {
        this.array = array;
        this.currentPart = currentPart;
        this.numOfElements = numOfElements;
        sum = 0;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run(){
        for (int c = currentPart*numOfElements; c < (currentPart+1)*numOfElements; c++){
            sum+=array[c];
        }
    }
}
