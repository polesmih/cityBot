package org.polesmih.command;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMAND = List.of(
            new BotCommand("/start", "запуск бота"),
            new BotCommand("/key", "клавиатура"),
            new BotCommand("/info", "о проекте"),
            new BotCommand("/donate", "благотворительность")
    );
}
