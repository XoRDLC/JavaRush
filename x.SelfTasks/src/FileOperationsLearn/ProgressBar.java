package FileOperationsLearn;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * Информация о процессе копирования
 */
public class ProgressBar {
    private long size = 0;
    private long tempSize = 0;

    public ProgressBar(Path source) throws IOException {
        FileVisitor<Path> fileVisitor = new Operations();
        Files.walkFileTree(source, fileVisitor);
        tempSize = size;
    }

    public long getRemainingBytes(Path path) {
        if (size == 0) return size;
        return tempSize -= path.toFile().length();
    }

    public double getRemainingPercent(Path path) {
        if (size == 0) return 1;
        return 1 - getRemainingBytes(path) / size;
    }

    public void printRemainingBytes(Path path) {
        if (size != 0) {
            System.out.printf("Remaining %s from %s%n", getRemainingBytes(path), size);
        } else {
            System.out.println("You need to calculate total size before use this method.");
        }
    }

    public void printRemainingPercent(Path path) {
        if (size != 0) {
            System.out.printf("Remaining %.2d%%", getRemainingPercent(path) * 100);
        } else {
            System.out.println("You need to calculate total size before use this method.");
        }
    }

    private class Operations extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            size += file.toFile().length();
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.SKIP_SUBTREE;
        }
    }
}
