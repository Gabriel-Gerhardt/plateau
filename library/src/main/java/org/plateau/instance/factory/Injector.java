package org.plateau.instance.factory;

import org.plateau.exception.NoValidConstructorException;
import org.plateau.instance.annotation.Inject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public class Injector {
    SingletonInstanceFactory singletonInstanceFactory;
    public Injector(SingletonInstanceFactory singletonInstanceFactory){
        this.singletonInstanceFactory = singletonInstanceFactory;
    }
    public void inject(Class<?> clazz) throws NoSuchMethodException {
        for(Constructor<?> constructor:clazz.getDeclaredConstructors()){
            for(Annotation annotation : constructor.getAnnotations()) {
                if (annotation.annotationType() == Inject.class) {
                    for(Class<?> parameter : constructor.getParameterTypes()){
                        if(singletonInstanceFactory.exists(parameter)!=null){
                            singletonInstanceFactory.getInstance(clazz, parameter)

                        }
                    }
                }
            }
        }
        throw new NoValidConstructorException("At least one of the constructors must have the interface");

    }
}
