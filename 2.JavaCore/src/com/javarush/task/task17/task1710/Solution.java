package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "-c": {
                    Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3]);
                    if (args[2].toLowerCase().equals("м")) {
                        allPeople.add(Person.createMale(args[1], date));
                    }
                    if (args[2].toLowerCase().equals("ж")) {
                        allPeople.add(Person.createFemale(args[1], date));
                    }
                    System.out.println(allPeople.size() - 1);
                }
                case "-u": {
                    Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4]);
                    Person person = allPeople.get(Integer.parseInt(args[1]));
                    person.setName(args[2]);
                    if (args[3].equals("м")) person.setSex(Sex.MALE);
                    if (args[3].equals("ж")) person.setSex(Sex.FEMALE);
                    person.setBirthDay(date);
                    break;
                }
                case "-d": {
                    Person person = allPeople.get(Integer.parseInt(args[1]));
                    person.setBirthDay(null);
                    person.setSex(null);
                    person.setName(null);
                    break;
                }
                case "-i": {
                    Integer index = Integer.parseInt(args[1]);
                    Person person = allPeople.get(index);
                    String sex = person.getSex() == Sex.FEMALE ? "ж" : "м";
                    System.out.printf("%s %te-%<tb-%<tY", person.getName() + " " + sex, person.getBirthDay(), Locale.ENGLISH);
                    break;
                }
                default: {
                    ;
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
