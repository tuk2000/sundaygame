package com.sunday.engine.events.driver;

import com.sunday.engine.common.enums.Driver;

public class KeyBoardEvent extends DriverEvent {
    public KeyBoardEvent() {
        super(Driver.Keyboard);
    }

    public enum Operation {
        KeyDown, KeyTyped, KeyUp
    }

    private Operation operation;
    private int key;
    private char character;

    public static KeyBoardEvent newKeyEvent(boolean isKeyDown, int key) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent();
        keyBoardEvent.operation = isKeyDown ? Operation.KeyDown : Operation.KeyUp;
        keyBoardEvent.key = key;
        return keyBoardEvent;
    }

    public static KeyBoardEvent newKeyEvent(char character) {
        KeyBoardEvent keyBoardEvent = new KeyBoardEvent();
        keyBoardEvent.operation = Operation.KeyTyped;
        keyBoardEvent.key = 0;
        keyBoardEvent.character = character;
        return keyBoardEvent;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getKey() {
        return key;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isPressed(int key) {
        return operation == Operation.KeyDown & this.key == key;
    }

    public boolean isTypedChar(char character) {
        return operation == Operation.KeyTyped & this.character == character;
    }

    @Override
    public String toString() {
        return  " from " + getSource().toString() + " " + getOperation().name() + " char : " + getCharacter() + " key : " + getKey();
    }

}
