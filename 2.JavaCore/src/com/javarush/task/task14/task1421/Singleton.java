package com.javarush.task.task14.task1421;

/**
 * Created by Dimitriy on 21.05.2017.
 */
public final class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
