package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int eval = 0;
        int[] homes = new int[15];
        for (int i = 0; i < homes.length; i++) {
            homes[i] = Integer.parseInt(bufferedReader.readLine());
            if (i % 2 == 0) {
                eval += homes[i];
            } else {
                eval -= homes[i];
            }
        }

        if (eval > 0) {
            System.out.println("В домах с четными номерами проживает больше жителей.");
        } else {
            System.out.println("В домах с нечетными номерами проживает больше жителей.");
        }


    }
}
