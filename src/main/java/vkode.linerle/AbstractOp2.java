package vkode.linerle;

public abstract class AbstractOp2<T, T1, T2, R> 
    extends AbstractOp<T, R> 
    implements Op2<T, T1, T2, R> {

    protected AbstractOp2(String name) {
        super(name);
    }
}
