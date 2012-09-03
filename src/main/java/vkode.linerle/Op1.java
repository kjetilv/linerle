package vkode.linerle;

public abstract class Op1<T1, R> 
    extends Op<R> 
    implements IOp1<T1, R> {

    protected Op1(String name) {
        super(name);
    }
}
