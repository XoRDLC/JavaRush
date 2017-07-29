package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        Map<String, BigDecimal> names = new TreeMap<>();
        BigDecimal max = new BigDecimal(0);
        while (reader.ready()) {
            String[] line = reader.readLine().split(" ");
            String name = line[0];
            BigDecimal value = new BigDecimal(line[1].replace(',', '.'));

            if (names.containsKey(name)) {
                value = value.add(names.get(name));
                names.replace(name, value);
            } else names.put(name, value);
            if (value.doubleValue() > max.doubleValue()) max = value;

        }
        reader.close();
        for (Map.Entry<String, BigDecimal> keys : names.entrySet()) {
            if (keys.getValue().doubleValue() == max.doubleValue()) System.out.println(keys.getKey());
        }
    }
}
