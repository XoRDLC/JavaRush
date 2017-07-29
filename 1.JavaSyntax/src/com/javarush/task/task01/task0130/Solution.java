package com.javarush.task.task01.task0130;

/* 
Наш первый конвертер!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(convertCelsiumToFahrenheit(39));
    }

    public static double convertCelsiumToFahrenheit(int celsium) {
        double far = 9.0 / 5.0;
        far *= celsium;
        far += 32;
        return far;
    }
}