package JavaRushTasks.x.SelfTasks.src.examplesCheck;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Dimitriy on 25.06.2017.
 */
public class E001_setOut {
    public static void main(String[] args) {
        PrintStream consoleStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        printSomething();

        String result = outputStream.toString();

        System.setOut(consoleStream);

        StringBuilder stringBuilder = new StringBuilder(result);
        stringBuilder.reverse();
        String reverseString = stringBuilder.toString();

        //выводим ее в консоль
        System.out.println(reverseString);
    }

    public static void printSomething() {
        System.out.println("Hi");
        System.out.println("My name is Amigo");
        System.out.println("Bye-bye!");
    }
}