package org.polesmih.handler;

import lombok.SneakyThrows;
import org.polesmih.bot.settings.ConfigSettings;
import org.polesmih.bot.settings.Sender;
import org.polesmih.gameTools.CheckLetters;
import org.polesmih.gameTools.CreateBotWord;
import org.polesmih.gameTools.FileManager;
import org.polesmih.gameTools.ListManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.polesmih.bot.settings.MessagesConst.*;


public class GameHandler extends TelegramLongPollingBot {

    private final static ConfigSettings settings = ConfigSettings.getInstance();
    String userCity;
    String botCity;
    Long chatId;

    Path pathToFileAllCities = Paths.get(settings.getPathCities());

    @Override
    public String getBotUsername() {
        return settings.getUserName();
    }

    @Override
    public String getBotToken() {
        return settings.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            userCity = update.getMessage().getText();
            chatId = update.getMessage().getChatId();

            Message message = update.getMessage();
            User from = message.getFrom();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());


            if (userCity.matches("[\\p{L} .'-]+")) {

                // если файл пользователя существует и он не пустой
//                if (FileManager.existsFile(from.getId())
//                ) {

                // и он не пустой
                if (!ListManager.userListIsEmpty(ListManager.createUserLinesCity(from.getId()))) {

/* Мы используем метод lines класса Files для создания потока строк в файле,
  а затем с помощью метода anyMatch проверяем, содержит ли какая-либо из строк
  искомое слово/термин. Если какая-либо строка содержит искомое слово,
  то метод anyMatch вернет true, и искомый термин будет считаться найденным.
  Но, если ни одна из строк не содержит искомого слова/термина,
  то метод @anyMatch вернет false, и искомый термин будет считаться не найденным.*/


                    try {
                        Stream<String> linesAllCities = Files.lines(pathToFileAllCities);
                        Stream<String> linesUser = Files.lines(FileManager.getPathUserFile(from.getId()));

                        //есть ли введенное слово в потоке срок файлов?
                        boolean foundAllCities = linesAllCities.anyMatch(line -> line.equalsIgnoreCase(userCity));
                        boolean foundUserCities = linesUser.anyMatch(line -> line.equalsIgnoreCase(userCity));


                        //если введенные названия есть в файле списка всех городов и не найдены в текущей сессии игры
                        if (foundAllCities && !foundUserCities && CheckLetters.checkFirstChar(userCity,
                                ListManager.createUserLinesCity(from.getId()))) {

                            FileManager.writeToFile(from.getId(),
                                    userCity.toUpperCase() + System.lineSeparator());


                            // проверить, не пустой ли список слов бота на нужную букву
                            if (CreateBotWord.emptyBotWord(
                                    ListManager.createFullLinesCity(),
                                    ListManager.createUserLinesCity(from.getId()), userCity)
                            ) {

                                execute(Sender.sendMessage(chatId, UPS));

                            } else {

                                botCity = CreateBotWord.createBotCity(
                                        ListManager.createFullLinesCity(),
                                        ListManager.createUserLinesCity(from.getId()), userCity
                                );

                                //записать слово бота в файл пользователя
                                FileManager.writeToFile(from.getId(),
                                        botCity.toLowerCase() + System.lineSeparator());

                                //вывести слово бота в телеграмм-бот
                                execute(Sender.sendMessage(chatId, botCity));

                            }


                        } else if (foundUserCities) {
                            execute(Sender.sendMessage(chatId, EXIST));


                        } else if (!CheckLetters.checkFirstChar(userCity,
                                ListManager.createUserLinesCity(from.getId()))) {
                            execute(Sender.sendMessage(chatId, CHECK_CHAR));


                        } else if (!foundAllCities) {
                            execute(Sender.sendMessage(chatId, NOT_FOUND));
                        }


                    } catch (IOException | TelegramApiException e) {
                        e.printStackTrace();
                    }

                }

                // если файл пустой - предупреждение пользователю о необходимости жать кнопку
                else if (ListManager.userListIsEmpty(ListManager.createUserLinesCity(from.getId()))) {
                    execute(Sender.sendMessage(chatId, START_GAME));
                }

            }

        }
    }

}
