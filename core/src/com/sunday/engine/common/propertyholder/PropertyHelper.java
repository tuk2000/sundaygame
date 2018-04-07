package com.sunday.engine.common.propertyholder;

import com.sunday.engine.environment.EnvironmentRelated;

public class PropertyHelper {
    public static boolean isSystemRelated(Object object) {
        return object.getClass().isAssignableFrom(SystemRelated.class);
    }

    public static boolean isEnvironmentRelated(Object object) {
        return object.getClass().isAssignableFrom(EnvironmentRelated.class);
    }

    public static boolean isResettable(Object object) {
        return object.getClass().isAssignableFrom(Resettable.class);
    }

    public static boolean isCustomized(Object object) {
        return object.getClass().isAssignableFrom(Customized.class);
    }
}
