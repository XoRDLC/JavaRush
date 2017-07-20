package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        HashMap<String, Cat> cats = new HashMap<String, Cat>();
        cats.put("Mike", new Cat("Mike"));
        cats.put("1", new Cat("1"));
        cats.put("2", new Cat("2"));
        cats.put("3", new Cat("3"));
        cats.put("4", new Cat("4"));
        cats.put("5", new Cat("5"));
        cats.put("6", new Cat("6"));
        cats.put("7", new Cat("7"));
        cats.put("8", new Cat("8"));
        cats.put("9", new Cat("9"));

        return cats;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        Set<Cat> cats = new HashSet<>();
        Iterable<Cat> iter = map.values();
        for (Cat cat : iter) {
            cats.add(cat);
        }
        return cats;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
