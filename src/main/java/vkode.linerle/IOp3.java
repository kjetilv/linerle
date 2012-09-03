package vkode.linerle;

public interface IOp3<T1, T2, T3, R> extends IOp<R> {

    R execute(T1 arg1, T2 arg2, T3 arg3);
}
