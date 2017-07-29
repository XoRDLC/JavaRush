/*
 * Copyright (c) 2017. Код создан Д.Кляусом. Для использования кода в коммерческих продуктах - свяжитесь @ : deadlords@mail.ru
 */


package JavaRushTasks.x.SelfTasks.src.examplesCheck;


import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

public class L002_TimeDateCalendar {
    public static void main(String[] args) {
        new L002_TimeDateCalendar().new TimeC().setPTime();



    }

    class TimeC {
        private Date time;

        public void setPTime(){
            System.out.println(Calendar.getInstance().getClass());
            for(String element: Calendar.getAvailableCalendarTypes()){
                System.out.println(element);
            }

            for(Locale element: Calendar.getAvailableLocales()){
                System.out.printf("%s\t", element.getCountry());
                //System.out.printf("%s\t", element.getDisplayCountry());
                //System.out.printf("%s\t", element.getDisplayName());
                //System.out.printf("%s\t", element.getDisplayScript());
                System.out.println();
            }
        }
    }
}
