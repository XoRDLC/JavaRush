package com.javarush.task.task06.task0606;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String val = bufferedReader.readLine();

        for (int i = 0; i < val.length(); i++) {
            if (val.charAt(i) % 2 == 0) {
                even++;
            } else {
                odd++;
            }

        }

        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
