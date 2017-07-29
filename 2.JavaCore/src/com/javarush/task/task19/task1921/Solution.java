package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {

        if (args.length != 0) {
            String fileName = args[0];
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(fileName));
                while (reader.ready()) {
                    String[] s = reader.readLine().split("[ ]+");
                    int year = Integer.parseInt(s[s.length - 1]);
                    int month = Integer.parseInt(s[s.length - 2]);
                    int day = Integer.parseInt(s[s.length - 3]);
                    StringBuffer name = new StringBuffer(s[0]);
                    for (int i = 1; i < s.length - 3; i++) {
                        name.append(" " + s[i]);
                    }
                    Date date = new Date(month + "/" + day + "/" + year);
                    PEOPLE.add(new Person(name.toString(), date));
                }
            } catch (FileNotFoundException e) {
                System.out.printf("файл \"%s\" не существует", fileName);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка в преобразованиях чисел");
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Накосячил с индексами");
            } finally {
                if (reader != null) reader.close();
            }
        } else {
            System.out.println("Запущено без аргументов.");
        }
    }
}
