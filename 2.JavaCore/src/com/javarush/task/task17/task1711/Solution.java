package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "-c":
                    synchronized (allPeople) {
                        for (int i = 0; i < (args.length - 1) / 3; i++) {
                            Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[3 + i * 3]);
                            if (args[2 + i * 3].toLowerCase().equals("м")) {
                                allPeople.add(Person.createMale(args[1 + i * 3], date));
                            }
                            if (args[2 + i * 3].toLowerCase().equals("ж")) {
                                allPeople.add(Person.createFemale(args[1 + i * 3], date));
                            }
                            System.out.println(allPeople.size() - 1);
                        }


                        break;
                    }
                case "-u":
                    synchronized (allPeople) {
                        for (int i = 0; i < (args.length - 1) / 4; i++) {
                            Date date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[4 + i * 4]);
                            Person person = allPeople.get(Integer.parseInt(args[1 + i * 4]));
                            person.setName(args[2 + i * 4]);
                            if (args[3 + i * 4].equals("м")) person.setSex(Sex.MALE);
                            if (args[3 + i * 4].equals("ж")) person.setSex(Sex.FEMALE);
                            person.setBirthDay(date);

                        }
                        break;
                    }
                case "-d":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            Person person = allPeople.get(Integer.parseInt(args[i]));
                            person.setBirthDay(null);
                            person.setSex(null);
                            person.setName(null);

                        }
                        break;

                    }
                case "-i":
                    synchronized (allPeople) {
                        for (int i = 1; i < args.length; i++) {
                            Integer index = Integer.parseInt(args[i]);
                            Person person = allPeople.get(index);
                            String sex = person.getSex() == Sex.FEMALE ? "ж" : "м";
                            System.out.printf("%s %te-%<tb-%<tY\n", person.getName() + " " + sex, person.getBirthDay(), Locale.ENGLISH);

                        }
                        break;
                    }
                default:
                    synchronized (allPeople) {
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
