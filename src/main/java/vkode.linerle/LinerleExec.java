package vkode.linerle;

public class LinerleExec {

    static <R> R execute(IOp<R> op, Object[] v) {
        if (op instanceof Op0) {
            return ((IOp0<R>)op).execute();
        }
        if (op instanceof Op1) {
            return op1((IOp1<?, R>) op, v);
        }
        if (op instanceof Op2) {
            return op2((IOp2<?, ?, R>) op, v);
        }
        if (op instanceof Op3) {
            return op3((IOp3<?, ?, ?, R>)op, v);
        }
        if (op instanceof Op4) {
            return op4((IOp4<?, ?, ?, ?, R>)op, v);
        }
        if (op instanceof Op5) {
            return op5((IOp5<?, ?, ?, ?, ?, R>) op, v);
        }
        return op6((IOp6<?, ?, ?, ?, ?, ?, R>)op, v);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1> R op1(IOp1<T1, R> IOp, Object[] v) {
        return IOp.execute((T1) v[0]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2> R op2(IOp2<T1, T2, R> IOp, Object[] v) {
        return IOp.execute((T1) v[0], (T2) v[1]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2, T3> R op3(IOp3<T1, T2, T3, R> IOp, Object[] v) {
        return IOp.execute((T1)v[0], (T2)v[1], (T3)v[2]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2, T3, T4> R op4(IOp4<T1, T2, T3, T4, R> IOp, Object[] v) {
        return IOp.execute((T1)v[0], (T2)v[1], (T3)v[2], (T4)v[3]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2, T3, T4, T5> R op5(IOp5<T1, T2, T3, T4, T5, R> IOp, Object[] v) {
        return IOp.execute((T1) v[0], (T2) v[1], (T3) v[2], (T4) v[3], (T5) v[4]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2, T3, T4, T5, T6> R op6(IOp6<T1, T2, T3, T4, T5, T6, R> IOp, Object[] v) {
        return IOp.execute((T1)v[0], (T2)v[1], (T3)v[2], (T4)v[3], (T5)v[4], (T6)v[5]);
    }

    private LinerleExec() {
        throw new IllegalStateException("Don't make me!");
    }
}
