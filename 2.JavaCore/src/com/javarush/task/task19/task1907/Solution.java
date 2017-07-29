package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        int wordsCount = 0;

        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(fileName));
        while (bufferedReader1.ready()) {
            String s = bufferedReader1.readLine();
            //System.out.println(s);
            String[] words = s.toString().split("\\W");
            for (String w : words) {
                if (w.equals("world")) wordsCount++;
            }
        }
        bufferedReader1.close();
        System.out.println(wordsCount);


    }
}
