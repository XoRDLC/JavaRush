/*
 * Copyright (c) 2017. Код создан Д.Кляусом. Для использования кода в коммерческих продуктах - свяжитесь @ : deadlords@mail.ru
 */

package examplesCheck;

public class E003_split {
    public static void main(String[] args) {

        String s = "aaa\\=sd=dfffe2 3 dfdf = dffd";
        String s2 = "asddd=4";
        String spl[] = s2.split("(!\\\\)=");
        for (String p : spl) {
            System.out.println(p);
        }
    }
}
