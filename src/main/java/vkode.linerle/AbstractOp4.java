package vkode.linerle;

public abstract class AbstractOp4<T, T1, T2, T3, T4, R> 
    extends AbstractOp<T, R> 
    implements Op4<T, T1, T2, T3, T4, R> {

    protected AbstractOp4(String name) {
        super(name);
    }
}
