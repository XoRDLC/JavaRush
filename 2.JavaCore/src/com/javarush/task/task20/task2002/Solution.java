package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();

            User user = new User();
            user.setMale(true);
            user.setFirstName("Dima");
            user.setLastName("Klyaus");
            user.setCountry(User.Country.RUSSIA);
            user.setBirthDate(new Date("03/26/1982"));
            javaRush.users.add(user);

            user = new User();
            user.setMale(true);
            user.setFirstName("Juriy");
            user.setLastName("Klyaus");
            user.setCountry(User.Country.UKRAINE);
            user.setBirthDate(new Date("03/13/1975"));
            javaRush.users.add(user);

            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(users.size() + "\n");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                writer.write(user.getFirstName() + "\n");
                writer.write(user.getLastName() + "\n");
                writer.write(user.getBirthDate().getTime() + "\n");
                writer.write(user.getCountry().getDisplayedName() + "\n");
                writer.write(user.isMale() + "\n");
            }
            //outputStream.flush();
            writer.close();
            //implement this method - реализуйте этот метод
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                User user = new User();

                user.setFirstName(reader.readLine());
                user.setLastName(reader.readLine());

                Date date = new Date();
                date.setTime(Long.parseLong(reader.readLine()));
                user.setBirthDate(date);
                String s = reader.readLine();

                for (User.Country c : User.Country.values()) {
                    if (c.getDisplayedName().equals(s)) {
                        user.setCountry(c);
                        break;
                    }
                }

                String b = reader.readLine();
                user.setMale(Boolean.parseBoolean(b));
                users.add(user);
            }

            reader.close();
            //implement this method - реализуйте этот метод
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
