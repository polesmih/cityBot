package org.polesmih.gameTools;

import lombok.SneakyThrows;
import org.polesmih.bot.settings.ConfigSettings;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileManager {

    private final static ConfigSettings settings = ConfigSettings.getInstance();

    public static Path getPathUserFile(long userId) {
        return Paths.get(settings.getPathUsers() + userId + ".txt");
    }

    @SneakyThrows
    public static void createFile(Long userId) {
        File userFile = new File(settings.getPathUsers() + userId + ".txt");
        if (!userFile.exists()) {
            userFile.createNewFile();
        } else {
            cleanFile(userId);
        }

    }

    public static void writeToFile(Long userId, String userWord) {
        try {
            // Создание файла, конвертация String в byte-массив и запись в файл (даже если файл существует)
            Files.write(
                    getPathUserFile(userId),
                    userWord.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static void cleanFile(Long userId) {

        //The FileChannel класс обеспечивает truncate() метод, который может обрезать файл до заданного размера.
        FileChannel.open(
                getPathUserFile(userId),
                StandardOpenOption.WRITE).truncate(0).close();
    }


}
