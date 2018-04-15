package com.sunday.engine.contextbank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.common.register.MappingRegister;
import com.sunday.engine.common.signal.DataSignal;

import java.lang.reflect.Constructor;

public class ContextBankImpl implements ContextBank {

    private MappingRegister<Data, DataContext> dataContextRegister = new MappingRegister<>();
    private MappingRegister<Class<? extends Data>, ClassContext> classContextRegister = new MappingRegister<>();

    private static void printDataMarkAnnotations(DataMark[] dataMarks) {
        for (DataMark dataMark : dataMarks) {
            System.out.println(dataMark.toString());
            System.out.println("DataType ");
            System.out.println(dataMark.type());
            System.out.println("Signal classes ");
            for (Class<? extends Signal> aClass : dataMark.signalClass()) {
                System.out.print(aClass.getName() + " ");
            }
            System.out.println();
            System.out.println("Context class");
            System.out.println(dataMark.contextClass().getName());
        }
    }

    @Override
    public <C extends DataContext> C getDataContext(Data data) {
        Class<C> contextClass;
        C context = null;
        if (dataContextRegister.hasKey(data)) {
            return (C) dataContextRegister.getValue(data);
        } else {
            Class clazz = data.getClass();
            try {
                if (clazz.isAnnotationPresent(DataMark.class)) {
                    DataMark dataMarks[] = (DataMark[]) clazz.getAnnotationsByType(DataMark.class);

                    // System.out.println("Data class " + clazz.getName());

                    //printDataMarkAnnotations(dataMarks);

                    contextClass = (Class<C>) dataMarks[0].contextClass();

                    Constructor<?>[] declaredConstructors = contextClass.getDeclaredConstructors();
                    Constructor defaultConstructor = null;
                    for (Constructor<?> constructor : declaredConstructors) {
                        if (constructor.getParameterCount() == 1 & (constructor.getParameterTypes()[0].isAssignableFrom(clazz))) {
                            defaultConstructor = constructor;
                            break;
                        }
                    }
                    context = (C) defaultConstructor.newInstance(data);
                    dataContextRegister.register(data, context);

                    ClassContext<C> classContext = getClassContext(clazz);
                    context.setPredicateConsumer((c) -> true, classContext);
                    context.setSignal(DataSignal.Add);
                    classContext.accept(context);

                } else {
                    throw new Exception("Unable to create context of data : " + data.toString());
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return context;
    }

    @Override
    public <D extends Data, RC extends DataContext<D>> ClassContext<RC> getClassContext(Class<D> clazz) {
        ClassContext<RC> classContext;
        if (!classContextRegister.hasKey(clazz)) {
            classContext = new ClassContext<>(clazz);
            classContextRegister.register(clazz, classContext);
        } else {
            classContext = classContextRegister.getValue(clazz);
        }
        return classContext;
    }
}
