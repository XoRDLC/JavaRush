package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        Integer count;
        String fileName;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        fileName = bufferedReader.readLine();
        bufferedReader.close();

        fileInputStream = new FileInputStream(fileName);
        count = 0;
        while (fileInputStream.available() > 0) {
            char c = (char) fileInputStream.read();
            if (c == ',') count++;

        }
        System.out.println(count);

        fileInputStream.close();

    }
}
