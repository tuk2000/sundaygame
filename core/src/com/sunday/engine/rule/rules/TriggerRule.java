package com.sunday.engine.rule.rules;

import com.sunday.engine.common.*;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.gamepad.GamePadSignal;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.driver.mouse.Mouse;
import com.sunday.engine.driver.mouse.MouseSignal;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.condition.DataCondition;
import com.sunday.engine.rule.condition.GamePadCondition;
import com.sunday.engine.rule.condition.KeyBoardCondition;
import com.sunday.engine.rule.condition.MouseCondition;
import com.sunday.engine.rule.tracer.SignalTracer;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class TriggerRule extends Rule {
    private SignalTracer signalTracer;
    private Condition condition;
    private Reaction reaction;

    private static Condition generateConditionFromSignal(Data data, Signal signal) {
        Condition condition = null;
        Class enumClazz = signal.getClass();
        Set<Data> dataSet = new HashSet<>();
        dataSet.add(data);
        Set<Predicate<Data>> predicateSet = new HashSet<>();
        if (enumClazz.equals(DataSignal.class)) {
            condition = new DataCondition(dataSet, predicateSet);
        } else if (enumClazz.equals(KeyBoardSignal.class)) {
            predicateSet.add(e -> ((KeyBoard) e).keyBoardSignal.equals(signal));
            condition = new KeyBoardCondition(dataSet, predicateSet);
        } else if (enumClazz.equals(MouseSignal.class)) {
            predicateSet.add(e -> ((Mouse) e).mouseSignal.equals(signal));
            condition = new MouseCondition(dataSet, predicateSet);
        } else if (enumClazz.equals(GamePadSignal.class)) {
            predicateSet.add(e -> ((GamePad) e).gamePadSignal.equals(signal));
            condition = new GamePadCondition(dataSet, predicateSet);
        } else if (enumClazz.equals(OutlookSignal.class)) {
            predicateSet.add(e -> ((Outlook) e).outlookSignal.equals(signal));
            condition = new DataCondition(dataSet, predicateSet);
        }
        return condition;
    }

    public TriggerRule(Condition condition, Reaction reaction) {
        super(condition, reaction);
    }

    public TriggerRule(Data data, Signal signal, Reaction reaction) {
        super(generateConditionFromSignal(data, signal), reaction);
        signalTracer = new SignalTracer(this, data, signal);
        tracers.add(signalTracer);
    }
}
