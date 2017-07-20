package com.javarush.task.task13.task1319;

import java.io.*;
import java.util.ArrayList;

/* 
Запись в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new FileWriter(reader.readLine()));
        String line = reader.readLine();
        ArrayList<String> list = new ArrayList<>();
        while (line != null) {
            list.add(line);
            if (line.equals("exit")) break;
            line = reader.readLine();

        }

        for (int i = 0; i < list.size(); i++) {
            writer.write(list.get(i) + "\n");
        }
        writer.close();
        reader.close();
    }
}
