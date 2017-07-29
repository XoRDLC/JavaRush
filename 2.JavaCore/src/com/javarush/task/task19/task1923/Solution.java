package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        String inFileName = args[0];
        String outFileName = args[1];

        BufferedReader reader = null;
        BufferedWriter writer = null;
        ArrayList<String> list = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(inFileName));
            while (reader.ready()) {
                String s[] = reader.readLine().split("[ ]+");
                for (String num : s) {
                    String t = num.replaceAll("[\\d]+", "");
                    if (t.length() < num.length()) list.add(num);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) reader.close();
        }


        try {
            writer = new BufferedWriter(new FileWriter(outFileName));
            for (String s : list) {
                writer.write(s + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }

    }
}
