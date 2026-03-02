package org.plateau.instance.factory;

import org.plateau.exception.InstanceCreationException;
import org.plateau.exception.InstanceMustImplementInterfaceException;
import org.plateau.exception.NoSuchInstanceException;
import org.plateau.exception.NoValidConstructorException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SingletonInstanceFactory {
    private Map<Class<?>, Object> classList = new HashMap<>();
    public SingletonInstanceFactory(){
    }
    private Object createSingletonInstance(Class<?> clazz, Object ... args)  {

        if (clazz.getInterfaces().length == 0) {
            throw new InstanceMustImplementInterfaceException("Any instance must implement at least one interface");
        }
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == args.length) {
                try {
                    if(!this.classList.containsKey(clazz)) {
                        Object instance = constructor.newInstance(args);
                        this.classList.put(clazz, instance);
                        return instance;
                    }
                    return classList.get(clazz);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new InstanceCreationException("Error creating singleton instance from class " + clazz.getName(), e);
                }
            }
        }
        throw new NoValidConstructorException("Not a valid constructor for this class " + clazz.getName());
    }
    public Object exists(Class<?> clazz){

        Object instance = this.classList.get(clazz);
        if(instance != null){
            return instance;
        }
        throw new NoSuchInstanceException("The wanted instance is not present");
    }
    public Object getInstance(Class<?> instance, Object ... args) {
        return createSingletonInstance(instance, args);
    }
}
