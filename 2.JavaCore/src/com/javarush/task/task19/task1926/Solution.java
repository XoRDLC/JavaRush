package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        BufferedReader file = null;

        try {
            file = new BufferedReader(new FileReader(fileName));
            while (file.ready()) {
                String s = file.readLine();
                System.out.println((new StringBuffer(s)).reverse());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (file != null) file.close();
        }


    }
}
