package com.javarush.task.task10.task1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> hash = new HashMap<>();
        while (true) {
            String temp = reader.readLine();
            if (temp.isEmpty()) break;

            int id = Integer.parseInt(temp);

            String name = reader.readLine();
            if (name.isEmpty()) break;

            hash.put(name, id);
        }

        for (HashMap.Entry<String, Integer> iter : hash.entrySet()) {
            System.out.println(iter.getValue() + " " + iter.getKey());
        }
    }
}
