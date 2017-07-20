package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream;
        String fileName;
        int count;


        fileName = args[0];


        fileInputStream = new FileInputStream(fileName);

        count = 0;
        if (fileInputStream.available() > 0) {

            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);


            for (int i = 0; i < buffer.length; i++) {
                char value = (char) buffer[i];
                if ((value >= 'A' && value <= 'Z') || (value >= 'a' && value <= 'z')) {
                    count++;
                }
            }
        }
        System.out.println(count);


        fileInputStream.close();

    }
}
