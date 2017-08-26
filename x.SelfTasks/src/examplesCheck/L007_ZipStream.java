package examplesCheck;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * https://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/
 *
 */

public class L007_ZipStream {

    public static void main(String[] args) throws IOException {
        startScheme();
    }


    public static void startScheme() throws IOException{
/*
        FileOutputStream fileOutputStream = new FileOutputStream("archive.zip");
        ZipOutputStream zip = new ZipOutputStream(fileOutputStream);

        zip.setLevel(9);
        zip.setComment("Test archive");

        Operations<Path> fileVisitor = new Operations<>();
        Files.walkFileTree(Paths.get("z:\\"), fileVisitor);

        for(Path path: fileVisitor.list) {
            zip.putNextEntry(new ZipEntry(path.toString()));
            File file = new File(path.toString());
            Files.copy(file.toPath(), zip);
        }
        zip.flush();
        zip.close();
        fileOutputStream.close();
*/


        FileInputStream fileInputStream = new FileInputStream("testDir.zip");
        ZipInputStream unzip = new ZipInputStream(new BufferedInputStream(fileInputStream));

        Path unpackPath = Paths.get("Z:/2");
        if(!Files.exists(unpackPath)) Files.createDirectory(unpackPath);

        ZipEntry zipEntry;
        while((zipEntry =  unzip.getNextEntry())!=null) {
            System.out.println("Unzipping " + zipEntry.getName());
            FileOutputStream fout = new FileOutputStream(zipEntry.getName());
            for (int c = unzip.read(); c != -1; c = unzip.read()) {
                fout.write(c);
            }
            unzip.closeEntry();
            fout.close();
        }

        unzip.close();
    }


    private static class Operations<T extends Object> extends SimpleFileVisitor<Path>{
        private ArrayList<Path> list = new ArrayList<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            list.add(file);
            return FileVisitResult.CONTINUE;
        }
    }
}
