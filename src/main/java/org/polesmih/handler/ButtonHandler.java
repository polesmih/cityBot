package org.polesmih.handler;

import lombok.SneakyThrows;
import org.polesmih.bot.settings.ConfigSettings;
import org.polesmih.bot.settings.DateFormatter;
import org.polesmih.bot.settings.Sender;
import org.polesmih.db.DbConnection;
import org.polesmih.db.WriteUser;
import org.polesmih.gameTools.CreateBotWord;
import org.polesmih.gameTools.FileManager;
import org.polesmih.gameTools.ListManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import static org.polesmih.bot.settings.MessagesConst.*;
import static org.polesmih.keyboard.enums.Buttons.*;

public class ButtonHandler extends TelegramLongPollingBot {

    long chatId;
    String messageText;
    String botCity;
    private final static ConfigSettings settings = ConfigSettings.getInstance();


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
        update.getUpdateId();
        chatId = update.getMessage().getChatId();
        messageText = update.getMessage().getText();

        Message message = update.getMessage();
        User from = message.getFrom();
        LocalDateTime localDateTime = LocalDateTime.now();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        if (update.hasMessage() && update.getMessage().hasText()) {

            if (messageText.equals(NEW.getButtonType())) {

                FileManager.cleanFile(from.getId());

                // сгенерировать случайное слово от бота и записать его в файл пользователя
                botCity = CreateBotWord.createStartBotCity(ListManager.createFullLinesCity());

                FileManager.writeToFile(
                        from.getId(),
                        botCity.toLowerCase() + System.lineSeparator());

                execute(Sender.sendMessage(chatId, botCity));


            } else if (messageText.equals(FINISH.getButtonType())) {

                // посчитать сроки, начинающиеся с заглавных букв (строки пользователя)

                WriteUser.writeUserCount(
                        localDateTime,
                        from.getId(),
                        from.getFirstName()
                );

                // обнулить файл пользователя
                FileManager.cleanFile(from.getId());
                execute(Sender.sendMessage(chatId, GAME_OVER));


            } else if (messageText.equals(RULES.getButtonType())) {
                execute(Sender.sendMessage(chatId, INSTRUCTION));


            } else if (messageText.equals(RESULT.getButtonType())) {

                String query = "SELECT date, user_count FROM visits WHERE user_id = " + from.getId();
                Statement statement = DbConnection.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                if (resultSet.next()) {
                    do {
                        String date = resultSet.getString("date");
                        String count = resultSet.getString("user_count");
                        String results = String.format("%s %s - %s\n",
                                DateFormatter.formattingDateTime(date), "результат ", count);

                        execute(Sender.sendMessage(chatId, results));
                    } while (resultSet.next());


                } else {
                    execute(Sender.sendMessage(chatId, EMPTY_DB));
                }

            }
        }
    }
}



