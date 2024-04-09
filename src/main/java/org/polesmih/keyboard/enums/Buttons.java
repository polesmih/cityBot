package org.polesmih.keyboard.enums;

public enum Buttons {

    NEW("Начать игру"),
    FINISH("Завершить игру"),
    RULES("Правила игры"),
    RESULT("Мои достижения");

    private final String buttonType;

    Buttons(String buttonType) {
        this.buttonType = buttonType;
    }

    public String getButtonType() {
        return buttonType;
    }

}
