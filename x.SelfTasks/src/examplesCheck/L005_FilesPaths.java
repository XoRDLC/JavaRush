package examplesCheck;

import java.io.File;
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
        Path source = Paths.get("D:/");
        Path target = Paths.get("z:/1");
        copyTo(source,target);
    }

    public static void copyTo(Path source, Path target) throws IOException{
        FileVisitor<Path> fileVisitor = new CopyClass(source, target);
        Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS),1, fileVisitor);
    }

    public static void moveTo(Path source, Path target) throws IOException{}

    //можно сделать общий подход к Move/Copy и менять только действия в зависимости от переданного параметра
    public static class CopyClass extends SimpleFileVisitor<Path>{
        private Path target = null;
        private Path source = null;

        public CopyClass(Path source, Path target) throws IOException {
            this.source = source;
            this.target = target;
            firstStart();
        }

        private void  firstStart() throws IOException {
            if(!Files.exists(this.target)){
                try{
                    Files.createDirectories(this.target);
                }
                catch (SecurityException|AccessDeniedException e){
                    System.out.println("Target directory not exitsts and not enough rights to create. " + e.getMessage());
                }
            }
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            Path path = Paths.get(target.toString(), this.source.relativize(dir).toString());
            System.out.println(Files.exists(path));
            System.out.println(Files.isDirectory(path));

            if(!Files.exists(path)) {
                try{
                    Files.createDirectories(path);}
                catch (SecurityException|AccessDeniedException e){
                    System.out.printf("Can't create the directory, not enougth rights: %s%n%s%n", path, e.getMessage());
                    return FileVisitResult.SKIP_SUBTREE;
                }
            }
            else {
                //на случай, если есть файл с именем как у директории, которую пытаемся сосздать
                if(!Files.isDirectory(path)) {
                    try {
                        //переименовать
                        Files.move(path, Paths.get(path + "_old"));
                        Files.createDirectory(path);
                    } catch (SecurityException | AccessDeniedException e) {
                        System.out.printf("Can't create directory \"%s\", because file with that name exist and blocked", path.getFileName());
                    }
                }
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String name = this.source.relativize(file).toString();
            Path path = Paths.get(target.toString(), Files.isDirectory(source)?name:name+ File.separator+file.getFileName());
            System.out.println("FILE: " + path);
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
            else {
                //запрос-ответ если файл с таким именим существует
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("FAIL: " + file);
            return FileVisitResult.CONTINUE;
        }
    }
}
