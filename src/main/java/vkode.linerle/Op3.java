package vkode.linerle;

public abstract class Op3<T1, T2, T3, R> 
    extends Op<R> 
    implements IOp3<T1, T2, T3, R> {

    protected Op3(String name) {
        super(name);
    }
}
