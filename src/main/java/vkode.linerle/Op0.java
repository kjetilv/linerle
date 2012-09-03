package vkode.linerle;

public abstract class Op0<T, R> 
    extends Op<R> 
    implements IOp0<R> {

    protected Op0(String name) {
        super(name);
    }
}
