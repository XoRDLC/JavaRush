package FileOperationsLearn;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Основной класс
 *
 * Задача А. Реализовать функции:
 * 1) копирование/перемещение папки(-ок), файла(-ов)
 * 2) подтверждения перезаписи/пропуска (для всех, один раз)
 * 3) обработку ошибок IO и прочих
 * 4) интерфейс с деревом (списком) для операций с файлами
 *
 *
 */


public class CopyMoveMain {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("d:/");
        Path target = Paths.get("Z:\\1");

        FileOperations.copyTo(source, target);
    }
}
