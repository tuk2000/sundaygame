package com.sunday.engine.event.driver;

import com.sunday.engine.driver.keyboard.KeyBoardData;
import com.sunday.engine.driver.keyboard.KeyBoardSignal;

public class KeyBoardEvent extends DriverEvent {
    public KeyBoardEvent(KeyBoardData keyBoardData) {
        super(keyBoardData);
    }

    private KeyBoardSignal operation;
    private int key;
    private char character;

    public static KeyBoardEvent newKeyEvent(KeyBoardData keyBoardData, boolean isKeyDown, int key) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent(keyBoardData);
        keyBoardEvent.operation = isKeyDown ? KeyBoardSignal.KeyDown : KeyBoardSignal.KeyUp;
        keyBoardEvent.key = key;
        return keyBoardEvent;
    }

    public static KeyBoardEvent newKeyEvent(KeyBoardData keyBoardData, char character) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent(keyBoardData);
        keyBoardEvent.operation = KeyBoardSignal.KeyTyped;
        keyBoardEvent.key = 0;
        keyBoardEvent.character = character;
        return keyBoardEvent;
    }

    public KeyBoardSignal getOperation() {
        return operation;
    }

    public int getKey() {
        return key;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isPressed(int key) {
        return operation == KeyBoardSignal.KeyDown & this.key == key;
    }

    public boolean isTypedChar(char character) {
        return operation == KeyBoardSignal.KeyTyped & this.character == character;
    }

    @Override
    public String toString() {
        return " from " + getSource().toString() + " " + getOperation().name() + " char : " + getCharacter() + " key : " + getKey();
    }

}
