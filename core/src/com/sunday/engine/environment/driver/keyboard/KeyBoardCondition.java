package com.sunday.engine.environment.driver.keyboard;

import com.sunday.engine.environment.driver.DriverCondition;
import com.sunday.engine.environment.driver.DriverContext;

import java.util.function.Predicate;

public class KeyBoardCondition extends DriverCondition<KeyBoard> {
    private static Predicate<DriverContext<KeyBoard>> getKeyPredicate(String keyName) {
        String newKeyName = keyName.length() == 1 ? keyName.toUpperCase() : keyName;
        return metaDataContext -> metaDataContext.getEnvironmentData().character.equals(newKeyName);
    }

    private static Predicate<KeyBoard> getKeyPredicate(int keyCode) {
        return keyBoard -> keyBoard.keyCode == keyCode;
    }

    public static KeyBoardCondition keyPressed() {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition();
        keyBoardCondition.setSignals(KeyBoardSignal.Pressed);
        keyBoardCondition.setExtraInfo("Key=[AnyKey]");
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyPressed(String keyName) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition();
        keyBoardCondition.setSignals(KeyBoardSignal.Pressed);
        keyBoardCondition.addPredicate(getKeyPredicate(keyName));
        keyBoardCondition.setExtraInfo("Key=[" + keyName + "]");
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyPressed(String... keyNames) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition();
        keyBoardCondition.setSignals(KeyBoardSignal.Pressed);
        String extraInfo = "Key=[";
        for (String keyName : keyNames) {
            keyBoardCondition.addPredicate(getKeyPredicate(keyName));
            extraInfo += keyName + "  ";
        }
        keyBoardCondition.setExtraInfo(extraInfo + "]");
        keyBoardCondition.setAndOperation(false);
        return keyBoardCondition;
    }

//    public static KeyBoardCondition keyCombination(String combination) {
//        KeyBoardCondition keyBoardCondition = new KeyBoardCondition();
//        String[] combo = combination.split("-");
//        Predicate<Data> keyPressed = keyBoard -> ((KeyBoard) keyBoard).keyBoardSignal == KeyBoardSignal.Pressed;
//        Predicate<Data> keyCombo = keyBoardState -> ((KeyBoardState) keyBoardState).currentTyped.getInfo().equals(combo.getInfo());
//        Map<Data, Predicate<Data>> clusters = keyBoardCondition.clusters;
//        clusters.put(keyBoard, keyPressed);
//        clusters.put(keyBoardState, keyCombo);
//        keyBoardCondition.setMainInfo("keyCombination " + combination);
//        return keyBoardCondition;
//    }

    public static KeyBoardCondition keyReleased(String keyName) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition();
        keyBoardCondition.setSignals(KeyBoardSignal.Released);
        keyBoardCondition.addPredicate(getKeyPredicate(keyName));
        keyBoardCondition.setExtraInfo("Key=[" + keyName + "]");
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyReleased(String... keyNames) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition();
        keyBoardCondition.setSignals(KeyBoardSignal.Released);
        String extraInfo = "Key=[";
        for (String keyName : keyNames) {
            keyBoardCondition.addPredicate(getKeyPredicate(keyName));
            extraInfo += keyName + "  ";
        }
        keyBoardCondition.setExtraInfo(extraInfo + "]");
        keyBoardCondition.setAndOperation(false);
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyTyped(String keyName) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition();
        keyBoardCondition.setSignals(KeyBoardSignal.Typed);
        keyBoardCondition.addPredicate(getKeyPredicate(keyName));
        keyBoardCondition.setExtraInfo("Key=[" + keyName + "]");
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyTyped(String... keyNames) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition();
        keyBoardCondition.setSignals(KeyBoardSignal.Typed);
        String extraInfo = "Key=[";
        for (String keyName : keyNames) {
            keyBoardCondition.addPredicate(getKeyPredicate(keyName));
            extraInfo += keyName + "  ";
        }
        keyBoardCondition.setExtraInfo(extraInfo + "]");
        keyBoardCondition.setAndOperation(false);
        return keyBoardCondition;
    }

    @Override
    protected void setExtraInfo(String info) {
        String tmp = "Type=[KeyBoardCondition]\n";
        super.setExtraInfo(tmp + info);
    }

}
