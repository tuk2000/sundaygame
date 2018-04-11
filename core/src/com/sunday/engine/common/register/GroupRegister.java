package com.sunday.engine.common.register;

import com.sunday.engine.common.Register;

import java.util.*;
import java.util.function.BiConsumer;

public class GroupRegister<K, V> implements Register<K, V>, UsingMultiValue<K, V> {
    protected Map<K, Set<V>> map = new HashMap<>();

    @Override
    public boolean hasKey(K k) {
        return map.containsKey(k);
    }

    @Override
    public boolean hasValue(V v) {
        boolean hasValue = false;
        for (K key : getKeys()) {
            for (V value : getValues(key)) {
                if (value.equals(v))
                    hasValue = true;
                break;
            }
            if (hasValue) break;
        }
        return hasValue;
    }

    @Override
    public void register(K k, V v) {
        if (hasKey(k)) {
            map.get(k).add(v);
        } else {
            HashSet<V> hashSet = new HashSet();
            hashSet.add(v);
            map.put(k, hashSet);
        }
    }

    @Override
    public void deregister(K k, V v) {
        if (hasKey(k)) {
            map.get(k).remove(v);
            if (map.get(k).isEmpty()) {
                map.remove(k);
            }
        }
    }

    @Override
    public void deregisterKey(K k) {
        if (hasKey(k)) {
            map.remove(k);
        }
    }

    @Override
    public void foreachPaar(BiConsumer<K, V> biConsumer) {
        map.forEach((key, set) -> {
            set.forEach(value -> biConsumer.accept(key, value));
        });
    }

    @Override
    public List<K> getKeys() {
        List<K> list = new ArrayList<>();
        map.keySet().forEach(list::add);
        return list;
    }

    @Override
    public K keyOf(V v) {
        for (K key : getKeys()) {
            if (getValues(key).contains(v)) {
                return key;
            }
        }
        return null;
    }

    @Override
    public List<V> getValues(K k) {
        List<V> list = new ArrayList<>();
        if (hasKey(k)) {
            (map.get(k)).forEach(list::add);
        }
        return list;
    }

    public void deregister(V v) {
        K k = keyOf(v);
        deregister(k, v);
    }
}
