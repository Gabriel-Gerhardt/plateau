package org.plateau.instance.factory;

import java.lang.reflect.InvocationTargetException;

public class SingletonInstanceFactory {
    private Class<?> clazz;
    public Object createSingletonInstance(String module){
        try {
            this.clazz = Class.forName(module);
            return clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
