package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        HashMap<Character, Integer> hashMap = new HashMap<>();
        ArrayList<Character> sortedMapKeys = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(fileName);

        while (fileInputStream.available() > 0) {
            char c = (char) fileInputStream.read();
            if (hashMap.containsKey(c)) hashMap.replace(c, hashMap.get(c) + 1);
            else {
                hashMap.put(c, 1);
            }
        }

        for (Map.Entry<Character, Integer> map : hashMap.entrySet()) {
            sortedMapKeys.add(map.getKey());
        }

        Collections.sort(sortedMapKeys);

        for (Character c : sortedMapKeys) {
            System.out.println(c + " " + hashMap.get(c));
        }

        fileInputStream.close();
    }
}
