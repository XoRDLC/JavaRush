package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();


    public static void main(String[] args) {
        String fileName1;
        String fileName2;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {

            fileName1 = bufferedReader.readLine();
            fileName2 = bufferedReader.readLine();
            bufferedReader.close();

            BufferedReader fileReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName1)));
            BufferedReader fileReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName2)));

            while (fileReader1.ready()) {
                allLines.add(fileReader1.readLine());
            }

            while (fileReader2.ready()) {
                forRemoveLines.add(fileReader2.readLine());
            }

            (new Solution()).joinData();

            fileReader1.close();
            fileReader1.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void joinData() throws CorruptedDataException {
        List<String> allLines2 = new ArrayList<String>(allLines);
        List<String> forRemoveLines2 = new ArrayList<>(forRemoveLines);


        for (int j = 0; j < allLines.size(); j++) {
            for (int i = 0; i < forRemoveLines2.size(); i++) {
                if (forRemoveLines2.get(i).equals(allLines.get(j))) {
                    forRemoveLines2.remove(i);
                    i = i - 1;
                }
            }
        }


        if (forRemoveLines2.size() > 0) {
            allLines.clear();
            throw new CorruptedDataException();
        } else {

            for (int i = 0; i < forRemoveLines.size(); i++) {
                for (int j = 0; j < allLines.size(); j++) {
                    if (allLines.get(j).equals(forRemoveLines.get(i))) {
                        allLines.remove(j);
                        j = j - 1;
                    }
                }
            }

        }
    }
}
