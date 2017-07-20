package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    Properties propertyVal = new Properties();

    public static void main(String[] args) throws Exception {

        Solution a = new Solution();
        a.fillInPropertiesMap();


        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("testFile2"));
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        a.save(writer);

    }

    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        FileInputStream file = new FileInputStream(fileName);
        load(file);
        file.close();

        //implement this method - реализуйте этот метод
    }

    public void save(OutputStream outputStream) throws Exception {
        propertyVal.clear();
        for (Map.Entry<String, String> map : properties.entrySet()) {
            propertyVal.put(map.getKey(), map.getValue());
        }
        if (true) throw new IOException();
        propertyVal.store(outputStream, "");

    }

    public void load(InputStream inputStream) throws Exception {
        propertyVal.load(inputStream);
        for (Map.Entry<Object, Object> map : propertyVal.entrySet()) {
            properties.put(map.getKey().toString(), map.getValue().toString());
        }

    }
}
