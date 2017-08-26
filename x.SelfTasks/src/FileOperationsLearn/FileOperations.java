package FileOperationsLearn;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 * Замечания:
 * 1) если один файл, то не нужно использовать FileVisitor
 */

public class FileOperations {
    public static void copyTo(Path source, Path target) throws IOException {
        FileVisitor fileVisitor = new Operations(source, target, true);
        Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), 2, fileVisitor);
    }

    public static void moveTo(Path source, Path target) throws IOException {
        FileVisitor fileVisitor = new Operations(source, target, false);
        Files.walkFileTree(source, fileVisitor);
    }

    private static class Operations implements FileVisitor<Path> {
        private Path source = null;
        private Path target = null;
        private boolean isCopy = true;
        private ProgressBar progressBar = null;

        //какая-то порочная идея, что конструктор выбрасывает ошибку не имеющую отношения к типам переменных
        //подумать, как лучше сделать создание папки назначения, если копируется один файл.
        public Operations(Path source, Path target, Boolean isCopy) throws IOException {
            this.source = source;
            this.target = target;
            this.isCopy = isCopy;
            createTargetDirectory();
            progressBar = new ProgressBar(source);
        }

        //создать целевую папку
        private void createTargetDirectory() throws IOException {
            if (!Files.exists(target)) {
                try {
                    Files.createDirectories(target);
                } catch (SecurityException | AccessDeniedException e) {
                    System.out.println("Target directory not exitsts and not enough rights to create. " + e.getMessage());
                    throw e;
                } catch (FileSystemException e) {
                    System.out.printf("Target path not reachable. Disk: %s not exists.%n", target.getRoot());
                    throw e;
                }
            }
        }


        //создать директории для копируемых/перемещаемых файлов
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Path path = Paths.get(target.toString(), this.source.relativize(dir).toString());

            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                } catch (SecurityException | AccessDeniedException e) {
                    System.out.printf("Can't create the directory, not enougth rights: %s%n%s%n", path, e.getMessage());
                    return FileVisitResult.SKIP_SUBTREE;
                }
            } else {
                //что делать: перезаписать, пропустить, заменить старые ..

                //на случай, если есть файл с именем как у директории, которую нужно создать
                if (!Files.isDirectory(path)) {
                    try {

                        Files.move(path, Paths.get(path + ".old"));
                        Files.createDirectory(path);
                    } catch (SecurityException | AccessDeniedException e) {
                        System.out.printf("Can't create directory \"%s\", because file with that name exist and blocked", path.getFileName());
                    }
                }
            }
            return FileVisitResult.CONTINUE;
        }

        //операции с файлами
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String name = this.source.relativize(file).toString();
            Path path = Paths.get(target.toString(), Files.isDirectory(source) ? name : name + File.separator + file.getFileName());

            if (!Files.exists(path)) {
                try {
                    if (isCopy)
                        Files.copy(file, path, StandardCopyOption.REPLACE_EXISTING);
                    else
                        Files.move(file, path, StandardCopyOption.REPLACE_EXISTING);
                } catch (SecurityException | AccessDeniedException e) {
                    System.out.printf(
                            "Can't copy %s, not enough rights: %s%n\t%s%n",
                            Files.isDirectory(file) ? "directory" : "file",
                            e.getMessage(),
                            e.getClass()
                    );
                }
            } else {
                //запрос-ответ если файл с таким именим существует
                try {
                    Files.move(path, Paths.get(path + ".old"));
                } catch (SecurityException | AccessDeniedException e) {
                    System.out.printf("Can't rename file \"%s\", not enough rights", path.getFileName());
                }
            }
            progressBar.printRemainingBytes(Files.exists(file) ? file : path);
            return FileVisitResult.CONTINUE;
        }

        //ошибки IO
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("FAIL: " + file);
            return FileVisitResult.CONTINUE;
        }

        //удалить директорию после перемещения всего содержимого
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

            return FileVisitResult.CONTINUE;
        }
    }
}
