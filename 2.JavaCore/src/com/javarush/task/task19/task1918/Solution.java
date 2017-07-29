package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        String tagName = args[0];
        String fileName;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        fileName = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        StringBuilder file = new StringBuilder();
        while (fileReader.ready()) {
            file.append(fileReader.readLine());
        }
        fileReader.close();

        //может реализовать через стек?


        int innerTegsCounter = 0; //считаем вложенные теги
        ArrayList<String> lines = new ArrayList<>();

        for (int i = 0; i < file.length(); i++) {
            innerTegsCounter = 0;
            int startIndex = file.indexOf("<" + tagName, i);
            int endIndex = file.indexOf("</" + tagName, startIndex + 1);
            int startPoint = startIndex; //передаём значение в while

            if (startPoint > 0 && endIndex > 0) {
                while (true) {
                    startPoint = file.indexOf("<" + tagName, startPoint + 1);
                    if (startPoint > 0 && startPoint < endIndex) {
                        innerTegsCounter++;
                    } else break;
                }
                while (innerTegsCounter > 0) {
                    endIndex = file.indexOf("</" + tagName, endIndex + 1);
                    innerTegsCounter--;
                }
                i = startIndex + tagName.length();
                lines.add(file.substring(startIndex, endIndex + 2 + tagName.length() + 1));
            } else break;

        }
        for (String s : lines) {
            System.out.println(s);
        }

    }
}
