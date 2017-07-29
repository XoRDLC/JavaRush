package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileInputName = bufferedReader.readLine();
        String fileOutputName = bufferedReader.readLine();
        bufferedReader.close();

        FileReader fileReader = new FileReader(fileInputName);
        FileWriter fileWriter = new FileWriter(fileOutputName);
        boolean isEven = false;
        while (fileReader.ready()) {
            int i = fileReader.read();
            if (isEven) {
                isEven = false;
                fileWriter.write(i);
            } else {
                isEven = true;
            }
        }
        fileReader.close();
        fileWriter.close();


    }
}
