package vkode.linerle;

public interface Op5<T, T1, T2, T3, T4, T5, R> extends Op<T, R> {

    R execute(T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5);
}
