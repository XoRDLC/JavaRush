package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader;
        BufferedReader inputFileReader;
        BufferedWriter outputFileWriter;
        String outFileName;
        String inFileName;

        ArrayList<Integer> list = new ArrayList<>();

        fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        inFileName = fileNameReader.readLine();
        outFileName = fileNameReader.readLine();
        fileNameReader.close();


        inputFileReader = new BufferedReader(new FileReader(inFileName));
        while (inputFileReader.ready()) {
            String s[] = inputFileReader.readLine().split(" ");
            for (String num : s) {
                try {
                    list.add(Integer.parseInt(num));
                } catch (NumberFormatException e) {
                }

            }
        }
        inputFileReader.close();

        outputFileWriter = new BufferedWriter(new FileWriter(outFileName));
        for (Integer i : list) {
            outputFileWriter.write(i + " ");
        }
        outputFileWriter.close();


    }
}
