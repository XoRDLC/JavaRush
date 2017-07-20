package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = args[0];
        FileInputStream fileInputStream = new FileInputStream(fileName);
        int countSpaces = 0;

        if (fileInputStream.available() > 0) {
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            for (int i = 0; i < buffer.length; i++) {
                char value = (char) buffer[i];
                if (value == ' ') {
                    countSpaces++;
                }
            }
            System.out.printf("%.2f", (double) (countSpaces) / buffer.length * 100);
        }

        fileInputStream.close();

    }
}
