package org.polesmih.bot;

import lombok.SneakyThrows;
import org.polesmih.bot.settings.ConfigSettings;
import org.polesmih.bot.settings.Sender;
import org.polesmih.handler.ButtonHandler;
import org.polesmih.handler.GameHandler;
import org.polesmih.keyboard.ButtonTypes;
import org.polesmih.handler.CommandHandler;
import org.polesmih.command.CommandTypes;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.polesmih.bot.settings.MessagesConst.*;
import static org.polesmih.command.BotCommands.LIST_OF_COMMAND;


public class Bot extends TelegramLongPollingBot {
    CommandTypes commandType = new CommandTypes();
    ButtonTypes buttonType = new ButtonTypes();
    CommandHandler commandSelectionHandler = new CommandHandler();
    ButtonHandler buttonSelectionHandler = new ButtonHandler();
    GameHandler gameHandler = new GameHandler();

    private final static ConfigSettings settings = ConfigSettings.getInstance();
    String messageText;
    Long chatId;


    public void init() throws TelegramApiException {
        this.execute(new SetMyCommands(LIST_OF_COMMAND, new BotCommandScopeDefault(), null));
    }

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

            messageText = update.getMessage().getText();
            chatId = update.getMessage().getChatId();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());

            if (commandType.types().contains(messageText)) {
                commandSelectionHandler.onUpdateReceived(update);

            } else if (buttonType.types().contains(messageText)) {
                buttonSelectionHandler.onUpdateReceived(update);

            } else if (messageText.matches("[\\p{L} .'-]+")) {
                gameHandler.onUpdateReceived(update);
            }

            else {
                execute(Sender.sendMessage(chatId, UNKNOWN));
            }

        }

    }
}
