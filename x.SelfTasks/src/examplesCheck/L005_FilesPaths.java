package examplesCheck;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileVisitor;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 * Работа с Files, Paths (с пакетом nio?)
 *
 * Задачи:
 * 1) Функция копирования (перенос папки с содержимым, несколько файлов);
 *  а) Прогресс бар (отдельно собрать перечень файлов)
 *  б) Перезапись существующих файлов (реакция-ответ)
 *  в) побороть ситуацию, когда target-директория совпадает с названием файла, - ошибка NoSuchFileException.)
 * 2) Функция переноса (вывести файлы которые не перемещены, дир.не удалены)
 *
 * Примеры:
 * com.javarush.task.task31.task3103
 *
 */

public class L005_FilesPaths {

    public static void main(String[] args) throws IOException{
        Path source = Paths.get("d:/Users/");
        Path target = Paths.get("z:/1");


        copyTo(source,target);
    }

    public static void copyTo(Path source, Path target) throws IOException{

        FileVisitor<Path> fileVisitor = new CopyClass(source, target);
        Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS),1, fileVisitor);

    }

    public static void moveTo(Path source, Path target) throws IOException{
        Files.walkFileTree(source, new MoveClass(target));}


    public static class CopyClass extends SimpleFileVisitor<Path>{
        private Path target = null;
        private Path source = null;
        public int aaaa = 0;

        public CopyClass(Path source, Path target){
            this.source = source;
            this.target = target;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            Path path = Paths.get(target.toString(), this.source.relativize(dir).toString());

            if(!Files.exists(path)) {
                try{
                    Files.createDirectories(path);}
                catch (SecurityException|AccessDeniedException e){
                    System.out.printf("Can't create the directory, not enougth rights: %s%n%s%n", path, e.getMessage());
                    return FileVisitResult.SKIP_SUBTREE;
                }
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Path path = Paths.get(target.toString(), this.source.relativize(file).toString());

            if(!Files.exists(path)){
                try{
                    Files.copy(file, path, StandardCopyOption.REPLACE_EXISTING);
                }
                catch (SecurityException|AccessDeniedException e){
                    System.out.printf(
                            "Can't copy %s, not enough rights: %s%n\t%s%n",
                            Files.isDirectory(file) ?"directory":"file",
                            e.getMessage(),
                            e.getClass()
                    );
                }
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("FAIL: " + file);
            return FileVisitResult.CONTINUE;
        }
    }

    public static class MoveClass implements FileVisitor<Path>{

        private Path target;

        public MoveClass(Path target){
            this.target = target;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return null;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            return null;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return null;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return null;
        }
    }
}
