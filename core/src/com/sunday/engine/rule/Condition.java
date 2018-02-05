package com.sunday.engine.rule;

import com.sunday.engine.common.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

public abstract class Condition implements Data {
    protected Map<Data, Predicate<Data>> clusters;
    protected Map<Data, Tracer> tracers = new TreeMap<>();
    private Reaction reaction;

    public Condition(Map<Data, Predicate<Data>> clusters) {
        this.clusters = clusters;
    }

    public List<Data> getLiterals() {
        List<Data> result = new ArrayList<>();
        result.addAll(clusters.keySet());
        return result;
    }

    public List<Predicate<Data>> getClusters() {
        List<Predicate<Data>> result = new ArrayList<>();
        result.addAll(clusters.values());
        return result;
    }

    private boolean isSatisfied() {
        List<Boolean> result = new ArrayList<>();
        result.add(true);
        clusters.forEach((data, predicate) -> {
            result.add(predicate.test(data));
        });
        return result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean & aBoolean2)).get();
    }

    public Map<Data, Tracer> getTracers() {
        return tracers;
    }

    public void check() {
        if (isSatisfied() & reaction != null) {
            reaction.run();
        }
    }

    public void connect(Reaction reaction) {
        this.reaction = reaction;
    }
}
