package com.sunday.engine.environment.driver.keyboard;

import com.sunday.engine.environment.driver.DriverCondition;
import com.sunday.engine.environment.driver.DriverContext;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class KeyBoardCondition extends DriverCondition<KeyBoard> {

    protected KeyBoardCondition(Predicate<DriverContext<KeyBoard>> predicate) {
        super(predicate);
    }

    private static Predicate<DriverContext<KeyBoard>> getKeyPredicate(String keyName) {
        String newKeyName = keyName.length() == 1 ? keyName.toUpperCase() : keyName;
        return metaDataContext -> metaDataContext.getEnvironmentData().character.equals(newKeyName);
    }

    private static Predicate<DriverContext<KeyBoard>> getKeysPredicate(String... keyNames) {
        return Arrays.stream(keyNames).map(KeyBoardCondition::getKeyPredicate)
                .reduce((driverContextPredicate, driverContextPredicate2) -> driverContextPredicate.or(driverContextPredicate2)).get();
    }

    private static String getKeysInfoValue(String... keyNames) {
        return Arrays.stream(keyNames).collect(Collectors.joining(" "));
    }

    public static KeyBoardCondition keyPressed() {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(context -> true);
        keyBoardCondition.signalCondition.setSignals(KeyBoardSignal.Pressed);
        keyBoardCondition.setExtraInfoEntry("Key", "AnyKey");
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyPressed(String keyName) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(getKeyPredicate(keyName));
        keyBoardCondition.signalCondition.setSignals(KeyBoardSignal.Pressed);
        keyBoardCondition.setExtraInfoEntry("Key", keyName);
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyPressed(String... keyNames) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(getKeysPredicate(keyNames));
        keyBoardCondition.signalCondition.setSignals(KeyBoardSignal.Pressed);
        keyBoardCondition.setExtraInfoEntry("Key", getKeysInfoValue(keyNames));
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyReleased(String keyName) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(getKeyPredicate(keyName));
        keyBoardCondition.signalCondition.setSignals(KeyBoardSignal.Released);
        keyBoardCondition.setExtraInfoEntry("Key", keyName);
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyReleased(String... keyNames) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(getKeysPredicate(keyNames));
        keyBoardCondition.signalCondition.setSignals(KeyBoardSignal.Released);
        keyBoardCondition.setExtraInfoEntry("Key", getKeysInfoValue(keyNames));
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyTyped(String keyName) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(getKeyPredicate(keyName));
        keyBoardCondition.signalCondition.setSignals(KeyBoardSignal.Typed);
        keyBoardCondition.setExtraInfoEntry("Key", keyName);
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyTyped(String... keyNames) {
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(getKeysPredicate(keyNames));
        keyBoardCondition.signalCondition.setSignals(KeyBoardSignal.Typed);
        keyBoardCondition.setExtraInfoEntry("Key", getKeysInfoValue(keyNames));
        return keyBoardCondition;
    }

}
