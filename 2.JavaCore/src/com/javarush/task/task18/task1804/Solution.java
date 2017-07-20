package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int min = Integer.MAX_VALUE;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();

        FileInputStream fileInputStream = new FileInputStream(fileName);

        while (fileInputStream.available() > 0) {
            int value = fileInputStream.read();
            if (hashMap.containsKey(value)) {
                hashMap.replace(value, hashMap.get(value) + 1);
            } else {
                hashMap.put(value, 1);
            }
        }


        for (HashMap.Entry<Integer, Integer> iterator : hashMap.entrySet()) {
            if (iterator.getValue() == 1) {
                System.out.print(iterator.getKey() + " ");
            }
        }

        fileInputStream.close();
    }
}
