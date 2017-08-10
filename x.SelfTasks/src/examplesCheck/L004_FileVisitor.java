package examplesCheck;


import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Properties;


/**
 * Разобраться с интерфейсом FileVisitor и классом SimpleFileVisitor
 * Доки:
 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/FileVisitor.html
 * https://docs.oracle.com/javase/tutorial/essential/io/walk.html
 * https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/package-summary.html
 *
 * Заметки:
 * 1) Files.walkFileTree - метод прохода по дереву файлов.
 * 2) Реализуется через применение интерфейса FileVisitor или класса SimpleFileVisitor (который импл. интерфейс FileVisitor)
 * 3) В FileVisitor четыре метода:
 *  а) FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs) - вызывается перед работой с папкой (напр. создание папки)
 *  б) FileVisitResult postVisitDirectory(T dir, IOException exc) - после работы с папкой (напр. удаление после удаления всех файлов)
 *  в) FileVisitResult visitFile(T file, BasicFileAttributes attrs) - работа с файлами
 *  г) FileVisitResult visitFileFiled(T file, IOException exc) - файлы вызов которых привеёл к ошибке
 * 4) Соответственно, при использовании интерфейса - переопределить 4 метода; при использовании класса - только нужные.
 * 5) В классе Files два метода реализации walkFileTree:
 *  а) Files.walkFileTree(Path, FileVisitor)
 *  б) Files.walkFileTree(Path, Set<FileVisitOption>, int, FileVisitor)
 * --------
 *      - Path — путь к файлу / папке
 *      - FileVisitor — класс реализующий интерфейс FileVisitor или наследующий класс SimpleFileVisitor
 *      - Set<FileVisitOption> - есть только одна опция FileVisitOption.FOLLOW_LINKS, разрешает переход по символьным ссылкам (EnumSet.of(FOLLOW_LINKS))
 *      - int — глубина прохода по папкам. Для полного перебора всех вложенных папок - Integer.MAX_VALUE
 * --------
 * 6) BasicFileAttributes - набор аттрибутов общих для многих файловых систем, есть варианты:
 *  а) DosFileAttributes - для файловых систем поддерживающих аттрибуты "DOS"
 *  б) PosixFileAttributes - для файловых систем поддерживающих аттрибуты стандартов Portable Operating System Interface (POSIX)
 *  в) и др.  https://docs.oracle.com/javase/8/docs/api/java/nio/file/attribute/package-summary.html
 * 7) FileVisitResult - тип результата для FileVisitor, enum, четыре константы:
 *  а) CONTINUE - продолжать
 *  б) SKIP_SIBLINGS - продолжить без просмотра siblings (имеют общую родит.папку; элементы одного уровня) файла или папки
 *  в) SKIP_TREE - пропустить просмотра содержимого папки
 *  г) TERMINATE - преврвать просмотр
 *
 *
 * * Примеры JavaRush:
 * com.javarush.task.task31.task3102
 * com.javarush.task.task31.task3104
 */
public class L004_FileVisitor {

    public static void main(String[] args) throws IOException {
        String fileName = "fileList.txt";
        PrintStream defOut = System.out;

        {
            System.out.println("Первый подход");
        }

        System.setOut(new PrintStream(new FileOutputStream(getFileName(fileName))));

        FileVisitor<Path> fileVisitor = null;
        Path path = null;

        path = Paths.get("Z:\\");
        path = Paths.get("D:\\");

        //пример использования FileVisitor;
        fileVisitor = new InterfaceFVImplementation();

        //пример использования SimpleFileVisitor; фильтр по типу файла, вывод TXT-файлов.
        fileVisitor = new ClassSimpleFVImplementation();

        //пропуск папок если в них есть файл .access с полем Forbidden = true
        fileVisitor = new DirAccessClass();

        Files.walkFileTree(path, fileVisitor);

        {
            System.setOut(defOut);
            System.out.println("Второй подход");
        }

        System.setOut(new PrintStream(new FileOutputStream(getFileName(fileName))));

        //переход по символьным ссылкам, глубина вложенных папок - 2
        Files.walkFileTree(path, EnumSet.of(FileVisitOption.FOLLOW_LINKS),2, fileVisitor);
    }

    //поиск не занятого имени файла
    public static String getFileName(String fileName){
        int i = 0;
        StringBuilder sb = new StringBuilder(fileName);
        while(Files.exists(Paths.get(sb.toString()))){
            sb = new StringBuilder(fileName);
            int c = sb.lastIndexOf(".");
            if(c==-1) sb.append(String.format("",i++));
            else{
                sb.insert(c, i++);
            }
        }
        return sb.toString();
    }

    //пропуск папок если в них есть файл .access с полем Forbidden = true
    public static class DirAccessClass extends SimpleFVFileFailedSkipSubtree{
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException{
            //создание пути к .access в папке
            Path path = Paths.get(dir + File.separator + ".access");
            //файла нет, продолжать работу (работает если и AccessDenied к папке)
            if(!Files.exists(path))
                return FileVisitResult.CONTINUE;

            //сделал через класс Properties, с предположением, что в .access может быть много полей
            Properties properties = new Properties();
            properties.load(Files.newInputStream(path));

            if(properties.getProperty("forbidden")!=null&&properties.getProperty("forbidden").equals("true")){
                System.out.println("SKIP: " + dir);
                return FileVisitResult.SKIP_SUBTREE;
            }
            else{
                return FileVisitResult.CONTINUE;
            }
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
            System.out.println("FILE: " + file);
            return FileVisitResult.CONTINUE;
        }

    }



    //переопределение visitFileFailed, чтобы не переписывать во всех остальных классах
    public static class SimpleFVFileFailedSkipSubtree extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc){
            return FileVisitResult.SKIP_SUBTREE;
        }
    }

    //фильтр по типу файла, вывод TXT-файлов.
    //SimpleFileVisitor<Path> - Path задаёт тип первой переменной в методах
    // endWith(..,) чувствителен к регистру
    // file.endWith() не работает с файлами (т.е. в visitFile не использовать). endWith возвращает путь к файлу без имени
    // файла, но тут не работает даже так, если папка txt, то endWith вернёт false
    public static class ClassSimpleFVImplementation extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(file.toString().toLowerCase().endsWith("txt")){
                System.out.println("TXT: " + file);
            }
            return FileVisitResult.CONTINUE;
        }

        //неободимо переопределять, в SimpleFileVisitor по умолчанию выбрасывается ошибка, return не определён
        //в классе такой комментарий и код:
        //  Unless overridden, this method re-throws the I/O exception that prevented the file from being visited
        //      public FileVisitResult visitFileFailed(T file, IOException exc) throws IOException
        //      { Objects.requireNonNull(file);  throw exc; }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

    //Реализация 4-х методов интерфейса FileVisitor
    //вывод дерева в консоль с пометками для каждого метода
    //FileVisitor<Path> - Path задаёт тип первой переменной в методах
    public static class InterfaceFVImplementation implements FileVisitor<Path>{
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("PRE:\t " + dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("FILE:\t " + file.getFileName());
            return FileVisitResult.CONTINUE;
        }

        //SKIP_SUBTREE и CONTINUE в данном методе дают один результат. (доступа нет, папка пропускается
        //необходимо поинтернетить, к чему может привести CONTINUE в postVisitDirectory
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("SKIPPED: " + file + "\t" + exc);
            return FileVisitResult.SKIP_SUBTREE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println("POST:\t " + dir);
            return FileVisitResult.CONTINUE;
        }
    }
}
