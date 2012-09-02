package vkode.linerle;

public abstract class AbstractOp1<T, T1, R> 
    extends AbstractOp<T, R> 
    implements Op1<T, T1, R> {

    protected AbstractOp1(String name) {
        super(name);
    }
}
