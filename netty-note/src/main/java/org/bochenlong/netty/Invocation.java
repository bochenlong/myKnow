package org.bochenlong.netty;

import java.util.Arrays;

/**
 * Created by bcl on 2016/8/30.
 */
public class Invocation {
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] args;

    @Override
    public String toString() {
        return "Body{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", args=" + Arrays.toString(args) +
                '}';
    }

    public final String getClassName() {
        return className;
    }

    public final void setClassName(String className) {
        this.className = className;
    }

    public final String getMethodName() {
        return methodName;
    }

    public final void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public final Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public final void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public final Object[] getArgs() {
        return args;
    }

    public final void setArgs(Object[] args) {
        this.args = args;
    }
}
