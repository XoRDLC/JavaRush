package com.javarush.task.task01.task0140;

import java.util.Scanner;

/* 
Выводим квадрат числа
*/

public class Solution {
    public static void main(String[] args) {
        int a;
        Scanner scn = new Scanner(System.in);
        a = scn.nextInt();
        System.out.println(a * a);
    }
}