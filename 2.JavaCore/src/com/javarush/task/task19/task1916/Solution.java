package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fNameOne = reader.readLine();
        String fNameTwo = reader.readLine();
        reader.close();

        ArrayList<String> listFileOrigin = new ArrayList<>();
        ArrayList<String> listFileChanged = new ArrayList<>();

        BufferedReader firstFile = new BufferedReader(new FileReader(fNameOne));
        BufferedReader seconfFile = new BufferedReader(new FileReader(fNameTwo));

        while (firstFile.ready()) {
            listFileOrigin.add(firstFile.readLine());
        }
        firstFile.close();

        while (seconfFile.ready()) {
            listFileChanged.add(seconfFile.readLine());
        }
        seconfFile.close();

        int lastSameIndex = 0;
        boolean founded = false;

        for (int i = 0; i < listFileOrigin.size(); i++) {
            String value = listFileOrigin.get(i);
            founded = false;
            for (int j = lastSameIndex; j < lastSameIndex + 2 && j < listFileChanged.size(); j++) {
                if (listFileChanged.get(j).equals(value)) {
                    founded = true;
                    if (j == lastSameIndex) {
                        lines.add(new LineItem(Type.SAME, value));
                        lastSameIndex++;
                        break;
                    }
                    if (j == lastSameIndex + 1) {
                        lines.add(new LineItem(Type.ADDED, listFileChanged.get(j - 1)));
                        lines.add(new LineItem(Type.SAME, value));
                        lastSameIndex = lastSameIndex + 2;
                    }
                }
            }
            if (!founded) {
                lines.add(new LineItem(Type.REMOVED, value));
            }
        }
        for (int j = lastSameIndex; j < listFileChanged.size(); j++) {
            lines.add(new LineItem(Type.ADDED, listFileChanged.get(j)));
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
