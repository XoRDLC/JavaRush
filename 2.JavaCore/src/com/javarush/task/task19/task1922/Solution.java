package com.javarush.task.task19.task1922;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        BufferedReader file = null;
        try {
            ArrayList<String[]> list = new ArrayList<>();
            ArrayList<String> lines = new ArrayList<>();
            file = new BufferedReader(new FileReader(fileName));
            while (file.ready()) {
                String s = file.readLine();
                lines.add(s);
                list.add(s.split("[ ]+"));
            }
            for (int i = 0; i < lines.size(); i++) {
                int count = 0;
                for (String w : words) {
                    String s[] = list.get(i);
                    for (int j = 0; j < s.length; j++) {
                        if (w.equals(s[j])) {
                            count++;
                            break;
                        }
                    }
                }
                if (count == 2) System.out.println(lines.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (file != null) file.close();
        }


    }
}
