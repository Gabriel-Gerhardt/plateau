package org.plateau.instance.factory;

import java.lang.reflect.InvocationTargetException;

public class SingletonInstanceFactory {
    private SingletonInstanceFactory(){
    }
    private static Object createSingletonInstance(Class<?> instance){
        try {

            return instance.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public static Object getInstance(Class<?> instance){
        return createSingletonInstance(instance);
    }
}
