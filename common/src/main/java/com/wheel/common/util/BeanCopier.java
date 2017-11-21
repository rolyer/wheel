package com.wheel.common.util;

import com.google.common.base.Function;
import net.sf.cglib.core.Converter;

public class BeanCopier<F, T> implements Function<F, T> {

    private net.sf.cglib.beans.BeanCopier beanCopier;

    protected net.sf.cglib.beans.BeanCopier getBeanCopier() {
        return beanCopier;
    }

    protected void init() {
        this.beanCopier = net.sf.cglib.beans.BeanCopier.create(sourceClass, targetClass, false);
    }

    private Class<T> targetClass;

    private Class<F> sourceClass;

    private BeanCopier<T, F> reverse;

    public BeanCopier<T, F> reverse() {
        return reverse;
    }

    public Function<T, F> getReverse() {
        if (this.reverse != null) {
            return this.reverse;
        }
        BeanCopier<T, F> reverse = this.reverse;
        synchronized (this) {
            if (reverse == null) {
                reverse = new BeanCopier<T, F>();
                reverse.setSourceClass(this.getTargetClass());
                reverse.setTargetClass(this.getSourceClass());
                reverse.init();
            }
        }
        return reverse;
    }

    public void setReverse(BeanCopier<T, F> reverse) {
        this.reverse = reverse;
    }

    protected Class<T> getTargetClass() {
        return targetClass;
    }

    protected Class<F> getSourceClass() {
        return sourceClass;
    }

    public void setTargetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    public void setSourceClass(Class<F> sourceClass) {
        this.sourceClass = sourceClass;
    }

    public T afterCopy(F source, T target) {
        return target;
    }

    public T copy(F input) {
        try {
            T o = targetClass.newInstance();
            beanCopier.copy(input, o, null);
            return afterCopy(input, o);
        } catch (Exception e) {
            throw new RuntimeException("create object fail, class:" + targetClass.getName() + " ", e);
        }
    }

    //外部创建好实例后，传入进来，而不创建目标实例
    public void copy(F input, T output, Converter convert) {
        beanCopier.copy(input, output, convert);
    }

    public T apply(F input) {
        if (input == null) {
            return null;
        }
        return copy(input);
    }
}
