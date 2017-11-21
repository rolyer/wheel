package com.wheel.common.util;

/**
 * 类SimpleBeanCopier.java的实现描述：
 *
 */
public class BaseBeanCopier<F,T> extends BeanCopier<F,T> {


    public BaseBeanCopier(Class<F> sourceClass, Class<T> targetClass){
        setSourceClass(sourceClass);
        setTargetClass(targetClass);
        init();
    }

}