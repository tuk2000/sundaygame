package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.State;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class KeyBoardCondition extends Condition {
    public KeyBoardCondition(Map<Data, Predicate<Data>> clusters) {
        super(clusters);
    }

    private static class KeyBoardState implements State {
        public String[] currentTyped;
    }

    private static KeyBoard keyBoard;

    public static void setKeyBoard(KeyBoard keyBoard) {
        KeyBoardCondition.keyBoard = keyBoard;
    }

    private static KeyBoardState keyBoardState = new KeyBoardState();

    public static KeyBoardCondition keyCombination(String combination) {
        String[] combo = combination.split("-");
        Predicate<Data> keyPressed = keyBoard -> ((KeyBoard) keyBoard).keyBoardSignal == KeyBoardSignal.KeyDown;
        Predicate<Data> keyCombo = keyBoardState -> ((KeyBoardState) keyBoardState).currentTyped.toString().equals(combo.toString());
        Map<Data, Predicate<Data>> clusters = new HashMap<>();
        clusters.put(keyBoard, keyPressed);
        clusters.put(keyBoardState, keyCombo);
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(clusters);
        keyBoardCondition.setInfo("keyCombination " + combination);
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyDown(char character) {
        Predicate<Data> keyPressed = keyBoard -> ((KeyBoard) keyBoard).keyBoardSignal == KeyBoardSignal.KeyDown & ((KeyBoard) keyBoard).character == character;
        Map<Data, Predicate<Data>> clusters = new HashMap<>();
        clusters.put(keyBoardState, keyPressed);
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(clusters);
        keyBoardCondition.setInfo("keyDown " + character);
        return keyBoardCondition;
    }

    public static KeyBoardCondition keyUp(char character) {
        Predicate<Data> keyPressed = keyBoard -> ((KeyBoard) keyBoard).keyBoardSignal == KeyBoardSignal.KeyUp & ((KeyBoard) keyBoard).character == character;
        Map<Data, Predicate<Data>> clusters = new HashMap<>();
        clusters.put(keyBoardState, keyPressed);
        KeyBoardCondition keyBoardCondition = new KeyBoardCondition(clusters);
        keyBoardCondition.setInfo("keyUp " + character);
        return keyBoardCondition;
    }
}
