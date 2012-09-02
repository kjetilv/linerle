package vkode.linerle;

public interface Op2<T, T1, T2, R> extends Op<T, R> {
    
    R execute(T1 arg1, T2 arg2);
}
