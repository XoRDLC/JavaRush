package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        Integer max = 0;
        FileInputStream fileInputStream = new FileInputStream(fileName);

        while (fileInputStream.available() > 0) {
            int value = fileInputStream.read();
            if (hashMap.containsKey(value)) {
                hashMap.replace(value, hashMap.get(value) + 1);
                if (hashMap.get(value) > max) max = hashMap.get(value);

            } else {
                hashMap.put(value, 1);
            }
        }

        for (HashMap.Entry<Integer, Integer> iterator : hashMap.entrySet()) {
            if (iterator.getValue() == max) {
                System.out.print(iterator.getKey() + " ");
            }
        }

        fileInputStream.close();

    }
}
