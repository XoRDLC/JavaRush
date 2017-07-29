package com.javarush.task.task15.task1529;

/**
 * Created by Dimitriy on 24.05.2017.
 */
public class Plane implements Flyable {
    private int amount;


    public Plane(int amount) {
        this.amount = amount;
    }

    @Override
    public void fly() {
    }
}
