package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String inFileName = args[0];
        String outFileName = args[1];

        BufferedReader reader = null;
        BufferedWriter writer = null;
        boolean firstWrite = true;


        try {
            reader = new BufferedReader(new FileReader(inFileName));
            writer = new BufferedWriter(new FileWriter(outFileName));
            while (reader.ready()) {
                String s[] = reader.readLine().split("[ ]+");

                for (int i = 0; i < s.length; i++) {
                    if (s[i].length() > 6) {
                        if (firstWrite) {
                            writer.write(s[i]);
                            firstWrite = false;
                        } else {

                            writer.write("," + s[i]);
                        }
                    }
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
        }


    }
}
