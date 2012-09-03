package vkode.linerle;

public abstract class Op2<T1, T2, R> 
    extends Op<R> 
    implements IOp2<T1, T2, R> {

    protected Op2(String name) {
        super(name);
    }
}
