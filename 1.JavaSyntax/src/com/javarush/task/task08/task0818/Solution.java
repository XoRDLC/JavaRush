package com.javarush.task.task08.task0818;

import java.util.HashMap;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A1", 1010);
        hashMap.put("A2", 1020);
        hashMap.put("A3", 1030);
        hashMap.put("A4", 1040);
        hashMap.put("A5", 1050);
        hashMap.put("A6", 1060);
        hashMap.put("A7", 100);
        hashMap.put("A8", 1070);
        hashMap.put("A9", 100);
        hashMap.put("A0", 200);
        return hashMap;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        HashMap<String, Integer> copyMap = new HashMap<>(map);
        for (HashMap.Entry<String, Integer> iterator : copyMap.entrySet()) {
            if (iterator.getValue() < 500) {
                map.remove(iterator.getKey());
            }
        }
    }

    public static void main(String[] args) {

    }
}