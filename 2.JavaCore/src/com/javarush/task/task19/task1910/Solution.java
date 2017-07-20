package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputFileName = bufferedReader.readLine();
        String outputFileName = bufferedReader.readLine();
        bufferedReader.close();


        BufferedReader input = new BufferedReader(new FileReader(inputFileName));
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFileName));

        while (input.ready()) {
            String s = input.readLine();
            output.write(s.replaceAll("[\\p{Punct}\\n]", ""));
        }
        input.close();
        output.close();

    }
}
