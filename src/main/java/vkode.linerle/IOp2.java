package vkode.linerle;

public interface IOp2<T1, T2, R> extends IOp<R> {
    
    R execute(T1 arg1, T2 arg2);
}
