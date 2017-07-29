package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer i = 1;
        Integer nod = 1;
        {
            Integer x = (Integer.parseInt(bufferedReader.readLine()));
            if (x <= 0) throw new NumberFormatException();
            Integer y = (Integer.parseInt(bufferedReader.readLine()));
            if (y <= 0) throw new NumberFormatException();
            while (i <= x || i <= y) {
                if (x % i == 0 && y % i == 0) nod = i;
                i++;
            }
            System.out.println(nod);
        }

    }
}
