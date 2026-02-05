package org.plateau.instance.factory;

import java.lang.reflect.InvocationTargetException;

public class SingletonInstanceFactory {
    private SingletonInstanceFactory(){
    }
    private static Object createSingletonInstance(String module){
        try {
            Class<?> instance = Class.forName(module);
            return instance.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public static Object getInstance(String module){
        return createSingletonInstance(module);
    }
}
