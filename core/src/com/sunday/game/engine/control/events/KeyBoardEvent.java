package com.sunday.game.engine.control.events;

public class KeyBoardEvent extends InputEvent {
    public enum Operation {
        KeyDown, KeyTyped, KeyUp
    }

    private Operation operation;
    private int key;
    private char character;

    public KeyBoardEvent(Operation operation, int key) {
        super(InputSource.Keyboard);
        this.operation = operation;
        this.key = key;
    }

    public KeyBoardEvent(char character) {
        super(InputSource.Keyboard);
        this.operation = Operation.KeyTyped;
        this.key = 0;
        this.character = character;
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
        return getEventType().name() + " " + getSource().toString() + " " + getOperation().name() + " " + getCharacter() + " " + getKey();
    }

}
