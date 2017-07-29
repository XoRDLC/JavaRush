package com.javarush.task.task10.task1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Количество букв
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // алфавит
        String abc = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] abcArray = abc.toCharArray();

        ArrayList<Character> alphabet = new ArrayList<Character>();
        for (int i = 0; i < abcArray.length; i++) {
            alphabet.add(abcArray[i]);
        }

        // ввод строк
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            list.add(s.toLowerCase());
        }

        ArrayList<Integer> count = new ArrayList<>();
        for (int i = 0; i < alphabet.size(); i++) {
            count.add(0);
        }

        String s = "";
        for (String s1 : list) {
            s += s1;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < alphabet.size(); j++) {
                if (alphabet.get(j).equals(c)) {
                    count.set(j, count.get(j) + 1);
                    break;
                }
            }
        }
        int i = 0;
        for (Character c : alphabet) {
            System.out.println(c + " " + count.get(i++));
        }
    }

}
