package org.polesmih.bot.settings;

import com.vdurmont.emoji.EmojiParser;

public class MessagesConst {

    public final static String HELLO = "!  " +
            EmojiParser.parseToUnicode(":wink:") +
            "\n\nВ этой игре ты сможешь потренировать память " +
            EmojiParser.parseToUnicode(":muscle:") +
            "\nи проверить свои знания названий городов мира. " +
            EmojiParser.parseToUnicode(":earth_asia:");

    public final static String CHOOSE = "На появившейся клавиатуре выбери действие " +
            EmojiParser.parseToUnicode(":point_down:");

    public final static String START_GAME = "Так у нас ничего не получится  " +
            EmojiParser.parseToUnicode(":wink:") +
            "\nДля начала новой игры нажми на кнопку \"Начать игру\"" +
            EmojiParser.parseToUnicode(":point_down:");

    public final static String GAME_OVER = EmojiParser.parseToUnicode(":star:") +
            "   ИГРА ОКОНЧЕНА!  " + EmojiParser.parseToUnicode(":star:") +
            "\nДля начала новой игры нажми на кнопку \"Начать игру\"" +
            EmojiParser.parseToUnicode(":point_down:");

    public final static String EXIST = EmojiParser.parseToUnicode(":warning:") +
            "  Такое слово уже было, попробуй еще раз";

    public final static String CHECK_CHAR = EmojiParser.parseToUnicode(":exclamation:") +
            "  Нужно ввести слово, начинающееся на букву," +
            "которой закончилось слово от бота\n\n" +
            EmojiParser.parseToUnicode(":key:") +
            "(если это были буквы \"ё\" или \"й\", " +
            "то твое слово должно начинаться на \"Е\" или \"И\" соответственно)";

    public final static String NOT_FOUND = "В моей базе такого слова нет...  " +
            EmojiParser.parseToUnicode(":thinking:") +
            "\nПопробуй еще раз - может быть название города пишется иначе? " +
            "\nНапример, если там есть буква \"ё\"," +
            "\nто нужно писать именно её  " +
            EmojiParser.parseToUnicode(":upside_down:");

    public final static String UPS = "Упс... Вот это конфуз... " +
            EmojiParser.parseToUnicode(":confused:") +
            "\nВ моей базе не осталось городов " +
            EmojiParser.parseToUnicode(":flushed:") +
            "\n" + EmojiParser.parseToUnicode(":trophy:") +
            "  П О З Д Р А В Л Я Ю !!! " +
            "\nЧтобы твой результат записался, нажми кнопку \"Завершить игру\"" +
            EmojiParser.parseToUnicode(":point_down:");

    public final static String INSTRUCTION = EmojiParser.parseToUnicode(":small_orange_diamond:") +
            "  Каждый участник (бот и ты) " +
            "отправляет название любого реального города мира, " +
            "начинающееся на ту букву, которой оканчивается название предыдущего города. " +
            "\n\n" + EmojiParser.parseToUnicode(":small_orange_diamond:") +
            "  Если название города оканчивается на Ъ, Ь, Ы - " +
            "участник называет город на предпоследнюю букву." +
            "\n\n" + EmojiParser.parseToUnicode(":small_blue_diamond:") +
            "Если название города оканчивается на Ё, Й - " +
            "участник называет город на Е или И соответственно." +
            "\n\n" + EmojiParser.parseToUnicode(":small_red_triangle:") +
            "  В одной сессии игры названия повторять нельзя." +
            "\n\n" + EmojiParser.parseToUnicode(":small_orange_diamond:") +
            "  Если у тебя закончился запас городов - нажми кнопку \"Завершить игру\", " +
            "после чего количество названных тобой городов в текущей игровой сессии сохраняется." +
            "\nДля просмотра статистики нажми кнопку \"Мои достижения\".";

    public final static String EMPTY_DB = "Чтобы получить результат, " +
            "нужно хотя бы раз сыграть  " +
            EmojiParser.parseToUnicode(":nerd:") +
            "\nи нажать кнопку \"Завершить игру\"";

    public final static String DOG_SHELTER = "Сделай доброе дело - " +
            "помоги Фонду\n<a href=\"https://donate.priut.ru\">Помощь бездомным собакам</a> " +
            EmojiParser.parseToUnicode(":dog:");

    public final static String UNKNOWN = "Неизвестный запрос... " +
            EmojiParser.parseToUnicode(":thinking:");

    public final static String ABOUT_A = "Если хочешь проверить свои знания в искусстве," +
            "\nприглашаем воспользоваться ботом " +
            "\n<a href=\"https://t.me/ArtQuizzes_bot\">Игра \"Викторина\"</a>";

    public final static String ABOUT_B = "\nЕсли нужно заняться делами, " +
            "\nприглашаем воспользоваться ботом " +
            "\n<a href=\"https://t.me/SimpleForms_bot\">Формы документов</a>";

    public final static String ABOUT_C = "\nЕсли хочешь следить за своим весом, " +
            "\nприглашаем воспользоваться ботом " +
            "\n<a href=\"https://t.me/BodyMI_bot\">Индекс массы тела</a>";


}
