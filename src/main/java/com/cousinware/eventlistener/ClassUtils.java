package com.cousinware.eventlistener;

import java.lang.reflect.Method;

public class ClassUtils {

    public static void doesContainListener(Class<?>[] clazz) {
        for (Class<?> c : clazz) {
            System.out.println(c.getName());

        }
    }

    public static boolean hasAnnotation(Method clazz,
                                 Class<? extends Listener> annotation) {
        return clazz != null && clazz.isAnnotationPresent(annotation);
    }
}
