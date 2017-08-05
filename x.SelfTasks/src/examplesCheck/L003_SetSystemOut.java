package examplesCheck;

import java.io.*;

/**
 * Переопределение системного вывода.
 * Задачи:
 * 1) Перопределение вывода в консоль (после каждой второй текстовой строки выводить заданный текст)
 * 2) Есть последовательный вывод в консоль неких параметров, перенаправить вывод в файл.
 *
 *
 * Решение:
 *
 */

public class L003_SetSystemOut {
    public static void main(String args[]) throws Exception {
        L003_SetSystemOut classMethods = new L003_SetSystemOut();
        classMethods.schemeOfSetOut();
    }

    public static void schemeOfSetOut() throws Exception {
        PrintStream defOut = System.out;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        OutPrintlnStringWrapper opsw = new OutPrintlnStringWrapper(printStream, "------------");
        System.setOut(opsw);

        System.out.println("1");
        System.out.println("2");
        System.out.println(400);
        System.out.println("3");
        System.out.println("4");


        {
            opsw.close();
            printStream.close();
        }
        //блок записи в файл
        {
            baos.writeTo(new FileOutputStream("baos2.txt"));
            baos.close(); //подтверждение, что не имеет эффекта, блок FileWriter по-прежнему записывает в baos.txt

            FileWriter fileWriter = new FileWriter("baos.txt", true);
            fileWriter.write(baos.toString());
            fileWriter.close();
        }

        String result = baos.toString();
        System.setOut(defOut);
        System.out.println(result);

    }

    static class OutPrintlnStringWrapper extends PrintStream {
        private int counter = 0;
        private String additionalText = "";

        public OutPrintlnStringWrapper(PrintStream printStream, String string) {
            super(printStream);
            additionalText = string;
        }

        @Override
        public void println(String x) {
            super.println(x);
            if (counter++ == 1) {
                counter = 0;
                super.println(this.additionalText);
            }
        }
    }
}
