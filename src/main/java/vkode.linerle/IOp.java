package vkode.linerle;

public interface IOp<R> {

    int getArity();
    
    String getName();

    Class<?>[] getTypes();

    Class<R> getReturnType();
}
