package com.sunday.engine.persistance;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import java.util.function.BiConsumer;

public class RWControl extends SubSystem implements Disposable {
    protected RWControl(String name, SystemPort systemPort) {
        super(name, systemPort);
    }
    //creating Rule for RWController
   /** private Rule rwCtrlRule = new Rule(RWData.class, RWSignal.class, new Reaction<RWData, RWSignal>() {
        @Override
        public void accept(RWData rwData, RWSignal rwSignal) {
            switch (rwSignal){
                case BackUP:
                    //Hier müssen wir überprüfen wann wir die datei aus der Laufzeit des Programms speichern
                    //wollen
                    break;
                case DELCache:
                    //Delete Cache
                    break;
            }
        }
        @Override
        public BiConsumer<RWData, RWSignal> andThen(BiConsumer<? super RWData, ? super RWSignal> after) {
            return null;
        }
    });
**/
    @Override
    public void dispose() {
//        systemPort.deleteDataInstance(rwCtrlRule);
    }
}
