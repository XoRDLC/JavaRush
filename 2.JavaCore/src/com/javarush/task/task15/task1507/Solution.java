package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) {
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

    public static void printMatrix(int m, int n, Double value) {
    }

    public static void printMatrix(int m, int n, Character value) {
    }

    public static void printMatrix(int m, int n, Integer value) {
    }

    public static void printMatrix(int m, int n, Short value) {
    }

    public static void printMatrix(int m, int n, Byte value) {
    }

    public static void printMatrix(int m, int n, Boolean value) {
    }

    public static void printMatrix(int m, int n, int value) {
    }

    public static void printMatrix(int m, int n, float value) {
    }
}
