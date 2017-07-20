package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = bufferedReader.readLine();
        String fileName2 = bufferedReader.readLine();
        bufferedReader.close();

        FileInputStream fileInputStream = new FileInputStream(fileName1);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName2);

        if (fileInputStream.available() > 0) {
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);

            for (int i = buffer.length - 1; i >= 0; i--) {
                fileOutputStream.write(buffer[i]);
            }
        }

        fileInputStream.close();
        fileOutputStream.close();

    }
}
