package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream def = System.out;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream print = new PrintStream(out);
        System.setOut(print);

        testString.printSomething();

        CharSequence plus = "+";
        CharSequence minus = "-";
        CharSequence multi = "*";
        String line = out.toString();
        String[] numbers = out.toString().split("[\\D]+");
        int result = Integer.MIN_VALUE;

        if (line.contains(plus)) result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
        if (line.contains(minus)) result = Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]);
        if (line.contains(multi)) result = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);

        System.setOut(def);

        System.out.println(line + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.print("3 + 6 = ");
        }
    }
}

