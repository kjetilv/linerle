package vkode.linerle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LinerleCallbacks {

    private static final ConcurrentMap<UUID, Op<?, ?>> opCallbacks = new ConcurrentHashMap<>();

    private static final ConcurrentMap<Object, List<UUID>> instanceCallbacks = new ConcurrentHashMap<>();

    static final ObjectMapper objectMapper;

    private static final Pattern UUID_REGEX = 
        Pattern.compile(".*([a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}).*");

    private static final String prefix = "/linerle/linerle-rpc/";

    private static final int minLength = (prefix + "/" + UUID.randomUUID()).length();

    public static String getScript(Object instance) {
        List<UUID> uuids = instanceCallbacks.get(instance);
        StringBuilder sb = new StringBuilder();
        for (UUID uuid : uuids) {
            Op<?, ?> op = opCallbacks.get(uuid);
            sb.append("function ").append(op.getName()).append("(");
            for (int i = 0, arity = op.getArity(); i < arity; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append("arg").append(i);
            }
            sb.append(") {\n");
            sb.append("  $.ajax({\n");
            sb.append("    type: 'POST', \n");
            sb.append("    url: '").append(prefix).append(op.getName()).append("/").append(uuid).append("',\n");
            sb.append("    data: {\n");
            for (int i = 0, arity = op.getArity(); i < arity; i++) {
                sb.append("      arg").append(i).append(": arg").append(i).append(",\n");
            }
            sb.append("    }\n");
            sb.append("  }).done(function (msg) {\n");
            sb.append("     alert(msg);\n");
            sb.append("  });\n");
            sb.append("};\n");
        }
        return sb.toString();
    }

    public static <T> void defineCallback(Object instance, Op<T, ?> op) {
        UUID key = UUID.randomUUID();
        opCallbacks.put(key, op);
        if (!instanceCallbacks.containsKey(instance)) {
            instanceCallbacks.putIfAbsent(instance, new ArrayList<UUID>());
        }
        instanceCallbacks.get(instance).add(key);
    }

    static boolean processed(HttpServletRequest req, HttpServletResponse res) {
        String uri = req.getRequestURI();
        int length = uri.length();
        if (uri.contains(prefix) && length > minLength) {
            Matcher matcher = UUID_REGEX.matcher(uri);
            if (matcher.matches()) {
                String uuid = matcher.group(1);
                UUID callbackUUID = UUID.fromString(uuid);
                Op<?, ?> callback = opCallbacks.get(callbackUUID);
                Object[] values = inputValues(req, callback);
                Object returnValue = execute(callback, values);
                write(returnValue, getWriter(res));
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static Object[] inputValues(HttpServletRequest req, Op<?, ?> callback) {
        return values(callback.getTypes(), callback.getArity(),
                                             (Map<String, String[]>) req.getParameterMap());
    }

    private static void write(Object value, PrintWriter writer) {
        try {
            objectMapper.writeValue(writer, value);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write value " + value + " to " + writer, e);
        }
    }

    private static PrintWriter getWriter(HttpServletResponse res) {
        try {
            return res.getWriter();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to get writer: " + res, e);
        }
    }

    private static Object[] values(Class<?>[] types, int arity, Map<String, String[]> parameterMap) {
        Object[] values = new Object[arity];
        for (int i = 0; i < arity; i++) {
            String[] strings = parameterMap.get("arg" + i);
            values[i] = objectMapper.convertValue(strings[i], types[i]);
        }
        return values;
    }

    private static <R> R execute(Op<?, R> op, Object[] v) {
        if (op instanceof Op0) {
            return ((Op0<?, R>)op).execute();
        }
        if (op instanceof Op1) {
            return op1((Op1<?, ?, R>) op, v);
        }
        if (op instanceof Op2) {
            return op2((Op2<?, ?, ?, R>) op, v);
        }
        if (op instanceof Op3) {
            return op3((Op3<?, ?, ?, ?, R>)op, v);
        }
        if (op instanceof Op4) {
            return op4((Op4<?, ?, ?, ?, ?, R>)op, v);
        }
        if (op instanceof Op5) {
            return op5((Op5<?, ?, ?, ?, ?, ?, R>) op, v);
        }
        return op6((Op6<?, ?, ?, ?, ?, ?, ?, R>)op, v);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1> R op1(Op1<?, T1, R> op, Object[] v) {
        return op.execute((T1) v[0]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2> R op2(Op2<?, T1, T2, R> op, Object[] v) {
        return op.execute((T1) v[0], (T2) v[1]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2, T3> R op3(Op3<?, T1, T2, T3, R> op, Object[] v) {
        return op.execute((T1)v[0], (T2)v[1], (T3)v[2]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2, T3, T4> R op4(Op4<?, T1, T2, T3, T4, R> op, Object[] v) {
        return op.execute((T1)v[0], (T2)v[1], (T3)v[2], (T4)v[3]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2, T3, T4, T5> R op5(Op5<?, T1, T2, T3, T4, T5, R> op, Object[] v) {
        return op.execute((T1) v[0], (T2) v[1], (T3) v[2], (T4) v[3], (T5) v[4]);
    }

    @SuppressWarnings("unchecked")
    private static <R, T1, T2, T3, T4, T5, T6> R op6(Op6<?, T1, T2, T3, T4, T5, T6, R> op, Object[] v) {
        return op.execute((T1)v[0], (T2)v[1], (T3)v[2], (T4)v[3], (T5)v[4], (T6)v[5]);
    }
}
