package org.polesmih.bot.settings;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Sender {

    public static SendMessage sendMessage (Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);
        sendMessage.setParseMode("html");
        return sendMessage;
    }

}
