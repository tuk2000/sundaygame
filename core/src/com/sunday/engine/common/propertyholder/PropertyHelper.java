package com.sunday.engine.common.propertyholder;

public class PropertyHelper {
    public static boolean isSystemRelated(Object object) {
        return object.getClass().isAssignableFrom(SystemRelated.class);
    }

    public static boolean isResettable(Object object) {
        return object.getClass().isAssignableFrom(Resettable.class);
    }

    public static boolean isCustomized(Object object) {
        return object.getClass().isAssignableFrom(Customized.class);
    }
}
