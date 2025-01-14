package org.polesmih.keyboard;

import java.util.HashSet;
import java.util.Set;

import static org.polesmih.keyboard.enums.Buttons.*;


public class ButtonTypes {

    public Set<String> types() {
        Set<String> types = new HashSet<String>();
        types.add(NEW.getButtonType());
        types.add(FINISH.getButtonType());
        types.add(CURRENT.getButtonType());
        types.add(PROGRESS.getButtonType());

        return types;
    }

}
