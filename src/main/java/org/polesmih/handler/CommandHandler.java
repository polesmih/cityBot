package org.polesmih.handler;

import lombok.SneakyThrows;
import org.polesmih.bot.settings.ConfigSettings;
import org.polesmih.bot.settings.Sender;
import org.polesmih.gameTools.FileManager;
import org.polesmih.keyboard.ButtonKeyboard;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.polesmih.bot.settings.MessagesConst.*;
import static org.polesmih.command.enums.Commands.*;


public class CommandHandler extends TelegramLongPollingBot {

    long chatId;
    String messageText;
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

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());

        if (update.hasMessage() && update.getMessage().hasText()) {

            if (messageText.equals(START.getCommandType())) {

                FileManager.createFile(from.getId());

                execute(Sender.sendMessage(chatId, "Привет, " + message.getFrom().getFirstName() + HELLO));
                sendMessage.setText(CHOOSE);
                sendMessage.setReplyMarkup(new ButtonKeyboard().createKeyboard());
                execute(sendMessage);

            } else if (messageText.equals(KEY.getCommandType())) {
                sendMessage.setText(CHOOSE);
                sendMessage.setReplyMarkup(new ButtonKeyboard().createKeyboard());
                execute(sendMessage);

            } else if (messageText.equals(DONATE.getCommandType())) {
                execute(Sender.sendMessage(chatId, DOG_SHELTER));

            } else if (messageText.equals(INFO.getCommandType())) {
                execute(Sender.sendMessage(chatId, ABOUT_A));
                execute(Sender.sendMessage(chatId, ABOUT_B));
                execute(Sender.sendMessage(chatId, ABOUT_C));

            }


        }
    }

}
