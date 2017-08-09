package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов

1. На вход метода main подаются два параметра.
Первый — path — путь к директории, второй — resultFileAbsolutePath — имя файла, который будет содержать результат.
2. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
2.1. Если у файла длина в байтах больше 50, то удалить его (используй метод FileUtils.deleteFile).
2.2. Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
2.2.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
2.2.2. Переименовать resultFileAbsolutePath в ‘allFilesContent.txt‘ (используй метод FileUtils.renameFile).
2.2.3. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 2.2.1. Тела файлов разделять «n«.
Все файлы имеют расширение txt.


*/
public class Solution {
    static int count = 0;

    public static HashMap<File, String> files = new HashMap<>();
    public static void main(String[] args) throws IOException {
        String path = args[0];
        String resultFileAbsolutePath = args[1];

        File folder = new File(path);
        File result = new File(resultFileAbsolutePath);
        File resultRenamed = new File(result.getParent() + File.separator + "allFilesContent.txt");
        FileUtils.renameFile(result, resultRenamed);

        /*
        лучи здоровья комманде javarush.
        валидатор не проходил:
        1) Потому что создал поток не сразу после переименования, а после сортировки
        2) 7+ тестов не принимало, потому что поток "не закрыт" - закрывал во всех доступных извращенцу местах, в результате
        родилась вот такая ублюдочная конструкция. Открыл поток, сразу закрыл, а уже в момент записи снова открыл поток.
        Может я тупой, и не вижу очевидного косяка, но сейчас это какой-то бред.
         */

        //должен быть сразу после переименования, потомучто
        FileOutputStream fileOutputStream = new FileOutputStream(resultRenamed);
        //как только закрыл здесь - прошёл валидатор.
        fileOutputStream.close();


        ArrayList<File> files = new ArrayList<>();
        files = folderOpener(folder);
        sortFilesByName(files);

        //а хотел вот так.
        /*
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        */

        //вот теперb проходит. wat
        fileOutputStream = new FileOutputStream(resultRenamed);
        /*
        try{
            fileOutputStream = new FileOutputStream(resultRenamed);
        */

            for(File file: files){
                FileInputStream fileInputStream = new FileInputStream(file);

                byte buff[] = new byte[fileInputStream.available()];
                fileInputStream.read(buff);
                fileOutputStream.write(buff);

                fileOutputStream.flush();
                fileOutputStream.write("\n".getBytes());

                fileInputStream.close();
            }
            fileOutputStream.close();

        /*

        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(fileInputStream!=null) fileInputStream.close();
            if(fileOutputStream!=null) fileOutputStream.close();
        }

        */

    }

    public static void sortFilesByName(ArrayList<File> list){
        list.sort(new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return (o1.getName()).compareTo(o2.getName());
            }
        });
    }

    public static ArrayList<File> folderOpener(File directory){
        ArrayList<File> fileList = new ArrayList<>();
        File file = new File(directory.getAbsolutePath());

        for(File entry: file.listFiles()){
            if(entry.isDirectory()) {
                ArrayList<File> innerFiles = folderOpener(entry);
                for(File inner: innerFiles){
                    fileList.add(inner);
                }
            }else{
                if (entry.length() > 50) {
                    FileUtils.deleteFile(entry);
                } else {
                    fileList.add(entry);
                }
            }
        }
        return fileList;
    }

    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}
