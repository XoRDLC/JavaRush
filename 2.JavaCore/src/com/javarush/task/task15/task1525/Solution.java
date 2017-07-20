package com.javarush.task.task15.task1525;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();

    static {
        try {

            /*
            BufferedInputStream bufferedReader = new BufferedInputStream(new FileInputStream(Statics.FILE_NAME));
            String s = "";
            while (bufferedReader.available() > 0) {
                char symb = (char) bufferedReader.read();
                if (symb == '\n') {
                    lines.add(s);
                    s = "";
                } else {
                    s += symb;
                }
            }
            lines.add(s);
            */

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(Statics.FILE_NAME)));

            while (true) {
                String s = bufferedReader.readLine();
                if (s == null) break;
                lines.add(s);
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(lines);
    }
}
