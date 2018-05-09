package ru.itis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static class AgeCars {
        private int age;
        private int cars;

        public AgeCars(int age, int cars) {
            this.age = age;
            this.cars = cars;
        }

        public void setCars(int cars) {
            this.cars = cars;
        }

        public int getAge() {
            return age;
        }

        public int getCars() {
            return cars;
        }

        @Override
        public String toString() {
            return getAge() + " " + getCars();
        }
    }

    public static void main(String[] args) {
        try {
            Scanner cars = new Scanner(new File("cars.txt"));
            Scanner users = new Scanner(new File("users.txt"));
            String current;
            AgeCars[] ageCars = new AgeCars[new Scanner(System.in).nextInt()];
            String[] currentCar;
            current = cars.nextLine();
            currentCar = current.split(" ");
            int age = -1;
            int count = 0;
            int currentID = Integer.parseInt(currentCar[2]);
            int carNum = 1;
            String[] currentUser;
            boolean founded;
            while (cars.hasNext()) {
                current = cars.nextLine();
                currentCar = current.split(" ");
                if (Integer.parseInt(currentCar[2]) == currentID) {
                    carNum++;
                } else {
                    founded = false;
                    while (!founded && users.hasNext()) {
                        currentUser = users.nextLine().split(" ");
                        if (Integer.parseInt(currentUser[0]) == currentID) {
                            age = Integer.parseInt(currentUser[2]);
                            founded = true;
                        }
                    }
                    founded = false;
                    for (int c = 0; c < count && !founded; c++) {
                        if (ageCars[c].age == age) {
                            founded = true;
                            ageCars[c].setCars(ageCars[c].getCars() + carNum);

                        }
                    }
                    if(!founded){
                        ageCars[count] = new AgeCars(age, carNum);
                        count++;
                    }
                    carNum = 1;
                    currentID = Integer.parseInt(currentCar[2]);
                }
            }
            founded = false;
            while (!founded) {
                currentUser = users.nextLine().split(" ");
                if (Integer.parseInt(currentUser[0]) == currentID) {
                    age = Integer.parseInt(currentUser[2]);
                    founded = true;
                }
            }
            founded = false;
            for (int c = 0; c < count && !founded; c++) {
                if (ageCars[c].age == age) {
                    founded = true;
                    ageCars[c].setCars(ageCars[c].getCars() + carNum);

                }
            }
            if(!founded){
                ageCars[count] = new AgeCars(age, carNum);
                count++;
            }
            int coun = 9;
            for (int c = 0; c < count; c++){
                System.out.println(ageCars[c]);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Файл не найден");
        }
    }
}


