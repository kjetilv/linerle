package vkode.linerle;

public interface Op<T, R> {

    int getArity();
    
    String getName();
    
    String getPath(String prefix);
    
    Class<?>[] getTypes();
    
    Class<T> getTargetType();
    
    Class<R> getReturnType();
}
