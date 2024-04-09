package org.polesmih.gameTools;

import java.util.List;

public class CheckLetters {

    public static String getFirstChar(String city) {
        return String.valueOf(city.charAt(0));
    }


    // послучение последней буквы для сравнения (с пропуском "неправильных" букв)
    public static String getLastChar(String city) {
        int pos = city.length() - 1;
        char lastChar = city.toUpperCase().charAt(pos);

        if (city.toUpperCase().charAt(pos) == 'Ё') {
            return String.valueOf('Е');
        } else if (city.toUpperCase().charAt(pos) == 'Й') {
            return String.valueOf('И');
        } else if (lastChar == 'Ь' || lastChar == 'Ы' || lastChar == 'Ъ') {
            pos--;
        }

        return String.valueOf(city.toUpperCase().charAt(pos));
    }


    public static boolean checkFirstChar(String userCity, List<String> listCurrentGame) {
        return getFirstChar(userCity.toUpperCase()).equals(getLastChar(listCurrentGame.get(listCurrentGame.size() - 1)));
    }


}
