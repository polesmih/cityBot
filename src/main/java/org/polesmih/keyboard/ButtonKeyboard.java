package org.polesmih.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;

import static org.polesmih.keyboard.enums.Buttons.*;

public class ButtonKeyboard {

    public ReplyKeyboardMarkup createKeyboard() {

        KeyboardRow row1 = new KeyboardRow();
        row1.add(NEW.getButtonType());
        row1.add(FINISH.getButtonType());

        KeyboardRow row2 = new KeyboardRow();
        row2.add(CURRENT.getButtonType());
        row2.add(PROGRESS.getButtonType());

        return ReplyKeyboardMarkup.builder()
                .keyboard(Arrays.asList(row1, row2))
                .resizeKeyboard(true)
                .build();
    }

}
