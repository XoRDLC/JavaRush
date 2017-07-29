package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        BufferedReader reader = new BufferedReader(new FileReader(fileName));


        Map<String, Double> namesMap = new TreeMap<>();
        while (reader.ready()) {
            String[] line = reader.readLine().split(" ");
            try {
                String name = line[0];
                Double value = Double.parseDouble(line[1].replace(",", "."));
                if (namesMap.containsKey(name)) {
                    namesMap.replace(name, value + namesMap.get(name));
                } else {
                    namesMap.put(name, value);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ФИО без значения");
            }
        }
        reader.close();

        for (Map.Entry<String, Double> m : namesMap.entrySet()) {
            System.out.println(m.getKey() + " " + m.getValue());
        }

    }
}