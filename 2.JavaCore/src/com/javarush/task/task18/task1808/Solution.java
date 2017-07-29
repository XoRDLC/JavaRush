package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = bufferedReader.readLine();
        String fileName2 = bufferedReader.readLine();
        String fileName3 = bufferedReader.readLine();
        bufferedReader.close();

        FileInputStream fileInputStream1 = new FileInputStream(fileName1);
        FileOutputStream fileOutputStream1 = new FileOutputStream(fileName2);
        FileOutputStream fileOutputStream2 = new FileOutputStream(fileName3);


        if (fileInputStream1.available() > 0) {
            int size = fileInputStream1.available();

            for (int i = 0; i < (size % 2 == 0 ? size / 2 : size / 2 + 1); i++) {
                int value = fileInputStream1.read();
                fileOutputStream1.write(value);
            }

            while (fileInputStream1.available() > 0) {
                int value = fileInputStream1.read();
                fileOutputStream2.write(value);
            }
        }

        fileInputStream1.close();
        fileOutputStream1.close();
        fileOutputStream2.close();

    }
}
