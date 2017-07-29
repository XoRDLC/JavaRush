package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String targetFile;
        String sourceFile1;
        String sourceFile2;

        targetFile = bufferedReader.readLine();
        sourceFile1 = bufferedReader.readLine();
        sourceFile2 = bufferedReader.readLine();

        FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
        FileInputStream fileInputStream1 = new FileInputStream(sourceFile1);
        FileInputStream fileInputStream2 = new FileInputStream(sourceFile2);

        if (fileInputStream1.available() > 0) {
            byte[] buffer = new byte[fileInputStream1.available()];
            int count = fileInputStream1.read(buffer);
            fileOutputStream.write(buffer, 0, count);
        }
        if (fileInputStream2.available() > 0) {
            byte[] buffer = new byte[fileInputStream2.available()];
            int count = fileInputStream2.read(buffer);
            fileOutputStream.write(buffer, 0, count);
        }

        bufferedReader.close();
        fileOutputStream.close();
        fileInputStream1.close();
        fileInputStream2.close();

    }
}
