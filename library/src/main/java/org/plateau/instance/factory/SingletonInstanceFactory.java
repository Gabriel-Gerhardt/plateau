package org.plateau.instance.factory;

import org.plateau.exception.BeanCreationException;
import org.plateau.exception.NoValidConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingletonInstanceFactory {
    Map<String, Object> classList = new HashMap<>();
    public SingletonInstanceFactory(){
    }
    private Object createSingletonInstance(Class<?> clazz, Object ... args)  {

            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                System.out.println(constructor);
                if (constructor.getParameterCount() == args.length) {
                    try {
                        if(!this.classList.containsKey(clazz.getName())) {
                            Object instance = constructor.newInstance(args);
                            this.classList.put(clazz.getName(), instance);
                            return instance;
                        }
                        return classList.get(clazz.getName());
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new BeanCreationException("Error creating singleton bean from class " + clazz.getName(), e);
                    }
                }
            }
        throw new NoValidConstructorException("Not a valid constructor for this class " + clazz.getName());
    }
    public Object getInstance(Class<?> instance, Object ... args) {
        return createSingletonInstance(instance, args);
    }
}
