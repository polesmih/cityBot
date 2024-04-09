package org.polesmih.gameTools;

import lombok.SneakyThrows;
import org.polesmih.bot.settings.ConfigSettings;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ListManager {

    private final static ConfigSettings settings = ConfigSettings.getInstance();


    @SneakyThrows
    public static List<String> createFullLinesCity() {
        return Files.readAllLines(Paths.get(settings.getPathCities()));
    }

    @SneakyThrows
    public static List<String> createUserLinesCity(long userId) {
        return Files.readAllLines(FileManager.getPathUserFile(userId));
    }

    public static boolean userListIsEmpty(List<String> userLineCity) {
        return userLineCity.stream().findAny().isEmpty();
    }

    public static long countUserWord(long userId) {
        return createUserLinesCity(userId).stream()
                .map(str -> str.charAt(0))
                .filter(Character::isUpperCase)
                .count();
    }


}
