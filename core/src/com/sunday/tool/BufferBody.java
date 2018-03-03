package com.sunday.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BufferBody<T extends ToolExtenderUIController, C extends Object> {
    public List<C> bufferQueue = new ArrayList<>();
    public BiConsumer<T, C> function;

    public BufferBody(BiConsumer<T, C> function) {
        this.function = function;
    }

    public List<C> getBufferQueue() {
        return bufferQueue;
    }

    public BiConsumer<T, C> getFunction() {
        return function;
    }

    public void empty(T toolExtenderUIController) {
        bufferQueue.forEach(object -> function.accept(toolExtenderUIController, object));
        bufferQueue.clear();
    }
}
