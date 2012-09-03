package vkode.linerle;

public abstract class Op6<T1, T2, T3, T4, T5, T6, R> 
    extends Op<R> 
    implements IOp6<T1, T2, T3, T4, T5, T6, R> {

    protected Op6(String name) {
        super(name);
    }
}
