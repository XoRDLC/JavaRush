package examplesCheck;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/**
 * Разобраться с интерфейсом FileVisitor и классом SimpleFileVisitor
 *
 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/FileVisitor.html
 * https://docs.oracle.com/javase/tutorial/essential/io/walk.html
 *
 *
 *
 */
public class L004_FileVisitor {

    //из com.javarush.task.task31.task3102
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new LinkedList<>();
        File path = new File(root);
        Files.walkFileTree(path.toPath(), new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                list.add(file.toAbsolutePath().toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        return list;
    }

    public static void main(String[] args) throws IOException {
        List<String> list = getFileTree("testDir");
        for(String entry :list){
            System.out.println(entry);
        }
    }
}
