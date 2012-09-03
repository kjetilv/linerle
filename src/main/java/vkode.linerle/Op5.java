package vkode.linerle;

public abstract class Op5<T1, T2, T3, T4, T5, R> 
    extends Op<R> 
    implements IOp5<T1, T2, T3, T4, T5, R> {

    protected Op5(String name) {
        super(name);
    }
}
