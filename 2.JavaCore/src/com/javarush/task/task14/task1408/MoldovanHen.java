package com.javarush.task.task14.task1408;

/**
 * Created by Dimitriy on 20.05.2017.
 */
public class MoldovanHen extends Hen {
    private int eggsCount = 18;

    public int getCountOfEggsPerMonth() {
        return eggsCount;
    }

    public String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.MOLDOVA + ". Я несу " + eggsCount + " яиц в месяц.";
    }
}