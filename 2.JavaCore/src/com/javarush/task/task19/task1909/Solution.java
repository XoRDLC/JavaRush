package com.javarush.task.task19.task1909;

/* 
Замена знаков
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
            StringBuilder sb = new StringBuilder(input.readLine());
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '.') sb.replace(i, i + 1, "!");
            }
            output.write(sb.toString());
        }
        output.close();
        input.close();
    }
}
