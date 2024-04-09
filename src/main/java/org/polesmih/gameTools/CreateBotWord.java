package org.polesmih.gameTools;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class CreateBotWord {


    // создание слова от бота при старте игры
    public static String createStartBotCity(List<String> fullLinesCity) {

        // выбирает 1 случайный элемент из списка городов, которых нет в текущей игре
        return getRandomListElement(fullLinesCity);
    }

    public static List<String> listStartFromUserLetter(List<String> fullLinesCity, String userCity) {

        //создаем список из всех городов на нужную букву
        return fullLinesCity.stream()
                .filter(line -> line.startsWith(CheckLetters.getLastChar(userCity)))
                .collect(Collectors.toList());
    }

    public static List<String> listUniqueElements(List<String> fullLinesCity, List<String> userLinesCity, String userCity) {

        //создаем список из всех городов на нужную букву
        return findDifference(
                listStartFromUserLetter(fullLinesCity, userCity),
                userLinesCity);
    }

    public static String createBotCity(List<String> fullLinesCity, List<String> userLinesCity, String userCity) {
        return getRandomListElement(listUniqueElements(fullLinesCity, userLinesCity, userCity));
    }


    /* метод проверки, есть ли в текущем списке игры города из общего списка,
    по результатм проверки - создает список различий*/
    public static List<String> findDifference(List<String> listStartFromUserLetter, List<String> userLinesCity) {

        List<String> uniqueList = new ArrayList<>(listStartFromUserLetter);

        for (String s : userLinesCity) {
            uniqueList.removeIf((t) -> t.equalsIgnoreCase(s));
        }

        return uniqueList;
    }


    //выбрать 1 случайный элемент из передаваего списка
    public static String getRandomListElement(List<String> cities) {
        return cities.get(ThreadLocalRandom.current().nextInt(cities.size()));
    }


    public static boolean emptyBotWord(List<String> fullLinesCity, List<String> userLinesCity, String userCity) {

        // формирует список городов на нужную букву, которых нет в текущей игре
        List<String> uniqueElements = findDifference(
                listStartFromUserLetter(fullLinesCity, userCity),
                userLinesCity);

        return uniqueElements.stream().findAny().isEmpty();

    }


}
