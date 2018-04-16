package com.sunday.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class UIControllerBuffer<T extends ToolExtenderUIController, C extends Object> {
    private Map<BufferHead<C>, BufferBody<T, C>> bufferMap;
    private Map<C, BiConsumer<T, C>> singleObjectMap;

    public UIControllerBuffer() {
        bufferMap = new HashMap<>();
        singleObjectMap = new HashMap<>();
    }

    public void addBuffer(C object, BiConsumer<T, C> flushFunction) {
        singleObjectMap.put(object, flushFunction);
    }

    public void removeBuffer(Object object) {
        singleObjectMap.remove(object);
    }

    public void addBuffer(Class<C> clazz, boolean isAddition, BiConsumer<T, C> flushFunction) {
        BufferHead bufferHead = new BufferHead(clazz, isAddition);
        BufferBody<T, C> bufferBody = new BufferBody(flushFunction);
        bufferMap.put(bufferHead, bufferBody);
    }

    private List correspondList(Object object, boolean isAddition) {
        for (BufferHead<C> bufferHead : bufferMap.keySet()) {
            if (bufferHead.isSuited(object, isAddition))
                return bufferMap.get(bufferHead).getBufferQueue();
        }
        return new ArrayList();
    }

    public void addInstance(Object object) {
        correspondList(object, true).add(object);
    }

    public void removeInstance(Object object) {
        correspondList(object, false).add(object);
    }

    public void flush(T toolExtenderUIController) {
        bufferMap.values().forEach(tcBufferBody -> {
            tcBufferBody.empty(toolExtenderUIController);
        });
        singleObjectMap.forEach((c, tcBiConsumer) -> tcBiConsumer.accept(toolExtenderUIController, c));
    }
}
