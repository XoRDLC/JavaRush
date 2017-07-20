package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = fileNameReader.readLine();
        String outputFileName = fileNameReader.readLine();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName)));

        String[] s = bufferedReader.readLine().split(" ");
        for (String line : s) {
            Double d = Double.parseDouble(line);
            bufferedWriter.write(Math.round(d) + " ");
        }

        bufferedReader.close();
        bufferedWriter.close();
        fileNameReader.close();
    }
}
