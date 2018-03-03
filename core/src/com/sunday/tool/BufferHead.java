package com.sunday.tool;


public class BufferHead<T extends Object> {
    public Class<T> clazz;
    public Operation operation;

    public BufferHead(Class<T> clazz, boolean isAddition) {
        this.clazz = clazz;
        this.operation = isAddition ? BufferHead.Operation.Add : BufferHead.Operation.Deletion;
    }

    public boolean isSuited(Object object, boolean isAddition) {
        return object.getClass().equals(clazz) & (isAddition == operation.equals(Operation.Add));
    }

    public enum Operation {
        Add, Deletion
    }
}
