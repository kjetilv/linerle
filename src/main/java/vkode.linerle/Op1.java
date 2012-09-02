package vkode.linerle;

public interface Op1<T, T1, R> extends Op<T, R> {

    R execute(T1 arg1);
}
