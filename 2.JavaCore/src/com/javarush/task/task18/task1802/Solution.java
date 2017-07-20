package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        FileInputStream fileInputStream = new FileInputStream(fileName);
        int min = Integer.MAX_VALUE;

        while (fileInputStream.available() > 0) {

            int value = fileInputStream.read();
            if (value < min) min = value;

        }

        System.out.println(min);
        fileInputStream.close();
    }
}
