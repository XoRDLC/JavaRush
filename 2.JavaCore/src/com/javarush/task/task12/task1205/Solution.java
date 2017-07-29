package com.javarush.task.task12.task1205;

/* 
Определимся с животным
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getObjectType(new Cow()));
        System.out.println(getObjectType(new Dog()));
        System.out.println(getObjectType(new Whale()));
        System.out.println(getObjectType(new Pig()));
    }

    public static String getObjectType(Object o) {
        String oClass[] = (o.getClass().getName()).split("\\$");
        switch (oClass[1]) {
            case "Cow":
                return "Корова";
            case "Dog":
                return "Собака";
            case "Whale":
                return "Кит";
            default:
                return "Неизвестное животное";
        }


    }

    public static class Cow {
    }

    public static class Dog {
    }

    public static class Whale {
    }

    public static class Pig {
    }
}
