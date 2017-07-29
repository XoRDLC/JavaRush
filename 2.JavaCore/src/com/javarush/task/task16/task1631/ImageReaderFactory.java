package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

/**
 * Created by Dimitriy on 28.05.2017.
 */
public class ImageReaderFactory {
    public static ImageReader getImageReader(ImageTypes IMG) {
        if (IMG == ImageTypes.BMP) {
            return new BmpReader();
        } else if (IMG == ImageTypes.JPG) {
            return new JpgReader();
        } else if (IMG == ImageTypes.PNG) {
            return new PngReader();
        } else {
            throw new IllegalArgumentException("Неизвестный тип картинки");
        }
    }
}
