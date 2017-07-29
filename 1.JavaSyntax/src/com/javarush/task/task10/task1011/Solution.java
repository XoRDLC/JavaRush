package com.javarush.task.task10.task1011;

/* 
Большая зарплата
*/

public class Solution {
    public static void main(String[] args) {
        String s = "Я не хочу изучать Java, я хочу большую зарплату";
        int i = 0;
        while (i < 40) {
            System.out.println(s.substring(i++).trim());
        }
    }
}

