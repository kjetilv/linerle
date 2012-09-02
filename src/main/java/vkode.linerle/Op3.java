package vkode.linerle;

public interface Op3<T, T1, T2, T3, R> extends Op<T, R> {

    R execute(T1 arg1, T2 arg2, T3 arg3);
}
