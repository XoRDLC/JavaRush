package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = fileNameReader.readLine();
        fileNameReader.close();
        String id = (args[0]);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));


        while (true) {
            String s = bufferedReader.readLine();
            if (s == null) break;
            if (id.equals(s.split(" ")[0])) {
                System.out.println(s);
                break;
            }
        }

        bufferedReader.close();

    }
}
