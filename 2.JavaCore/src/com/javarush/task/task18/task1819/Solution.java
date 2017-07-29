package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = bufferedReader.readLine();
        String fileName2 = bufferedReader.readLine();
        bufferedReader.close();

        FileInputStream fileInputStream1 = new FileInputStream(fileName1);
        FileInputStream fileInputStream2 = new FileInputStream(fileName2);


        if (fileInputStream2.available() > 0) {
            byte[] buffer = new byte[fileInputStream2.available()];
            fileInputStream2.read(buffer);
            if (fileInputStream1.available() > 0) {
                byte[] buffer2 = new byte[fileInputStream1.available()];
                fileInputStream1.read(buffer2);
                FileOutputStream fileOutputStream = new FileOutputStream(fileName1);
                fileOutputStream.write(buffer, 0, buffer.length);
                fileOutputStream.write(buffer2, 0, buffer2.length);
                fileOutputStream.close();
            }

        }
        fileInputStream1.close();
        fileInputStream2.close();
    }
}
