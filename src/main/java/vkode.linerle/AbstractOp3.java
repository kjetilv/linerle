package vkode.linerle;

public abstract class AbstractOp3<T, T1, T2, T3, R> 
    extends AbstractOp<T, R> 
    implements Op3<T, T1, T2, T3, R> {

    protected AbstractOp3(String name) {
        super(name);
    }
}
