package vkode.linerle;

public abstract class Op4<T1, T2, T3, T4, R> 
    extends Op<R> 
    implements IOp4<T1, T2, T3, T4, R> {

    protected Op4(String name) {
        super(name);
    }
}
