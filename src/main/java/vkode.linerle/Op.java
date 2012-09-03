package vkode.linerle;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Op<R> implements IOp<R> {

    private static final Class<?>[] NONE = new Class<?>[0];

    private static final Map<Class<?>, Class<?>> targetTypes = new HashMap<Class<?>, Class<?>>(); 
    
    private static final Map<Class<?>, Class<?>[]> argsTypes = new HashMap<Class<?>, Class<?>[]>(); 
    
    private static final Map<Class<?>, Class<?>> returnTypes = new HashMap<Class<?>, Class<?>>(); 
    
    private final String name;

    private final int arity;

    private final Class<?>[] argTypes;

    private final Class<R> returnType;

    @SuppressWarnings("unchecked")
    protected Op(String name) {
        this.name = name;
        if (targetTypes.containsKey(getClass())) {
            this.returnType = (Class<R>) returnTypes.get(getClass());
            this.argTypes = argsTypes.get(getClass());
            this.arity = this.argTypes.length - 1;
        } else {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            int length = actualTypeArguments.length;
            this.arity = length - 1;
            this.argTypes = new Class<?>[this.arity];
            for (int i = 0; i < arity; i++) {
                argTypes[i] = (Class<?>) actualTypeArguments[i];
            }
            returnType = (Class<R>) actualTypeArguments[length - 1];
        }
    }
    
    @Override
    public final int getArity() {
        return arity;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final Class<?>[] getTypes() {
        return argTypes.clone();
    }

    @Override
    public final Class<R> getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + 
               getName() + Arrays.toString(argTypes) +  " => " + getReturnType() + 
               "]"; 
    }
}
