package examplesCheck;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


/**
 * Работа с Files, Paths (с пакетом nio?)
 */

public class L005_FilesPaths {

    public static void main(String[] args) {
        for (FileVisitOption c : FileVisitOption.values())
            System.out.println(c);
    }


    //com.javarush.task.task31.task3103
    public static byte[] readBytes(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(fileName));
    }

    public static List<String> readLines(String fileName) throws IOException {

        return Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        Files.write(Paths.get(fileName), bytes);
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
        Files.copy(Paths.get(resourceFileName), Paths.get(destinationFileName));
    }
}
