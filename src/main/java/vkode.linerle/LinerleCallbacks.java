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

public final class LinerleCallbacks {
    
    private static final ConcurrentMap<UUID, Op<?>> opCallbacks = new ConcurrentHashMap<>();

    private static final ConcurrentMap<Object, List<UUID>> instanceCallbacks = new ConcurrentHashMap<>();

    private static final Pattern UUID_REGEX =
        Pattern.compile(".*([a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}).*");

    private static final int minLength =
        (Linerle.getContextPath() + "/" + Linerle.getPrefix() + "/" + UUID.randomUUID()).length();

    public static String getScript(Object instance) {
        List<UUID> uuids = instanceCallbacks.get(instance);
        StringBuilder sb = new StringBuilder();
        for (UUID uuid : uuids) {
            Op<?> op = opCallbacks.get(uuid);
            sb.append("function ").append(op.getName()).append("(");
            for (int i = 0, arity = op.getArity(); i < arity; i++) {
                sb.append("arg").append(i).append(", ");
            }
            sb.append("callback) {\n");
            sb.append("  $.getJSON('").append(Linerle.getContextPath()).append(Linerle.getPrefix()).append("/").append(op.getName()).append("/").append(uuid).append("',\n");
            sb.append("            { ");
            for (int i = 0, arity = op.getArity(); i < arity; i++) {
                sb.append("arg").append(i).append(": ");
                sb.append("linerle_input_value(arg").append(i).append(")");
                if (i + 1 < arity) {
                    sb.append(",\n              ");
                }
            }
            sb.append(" },\n");
            sb.append("            callback);\n");
            sb.append("};\n");
        }
        return sb.toString();
    }

    public static void define(Object instance, Op<?> op) {
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
        if (uri.contains(Linerle.getPrefix()) && length > minLength) {
            Matcher matcher = UUID_REGEX.matcher(uri);
            if (matcher.matches()) {
                String uuid = matcher.group(1);
                UUID callbackUUID = UUID.fromString(uuid);
                Op<?> callback = opCallbacks.get(callbackUUID);
                if (callback != null) {
                    Object[] values = inputValues(req, callback);
                    Object returnValue = LinerleExec.execute(callback, values);
                    LinerleJSON.write(returnValue, getWriter(res));
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static Object[] inputValues(HttpServletRequest req, Op<?> callback) {
        return LinerleJSON.values(callback.getTypes(), callback.getArity(),
                                  (Map<String, String[]>) req.getParameterMap());
    }

    private static PrintWriter getWriter(HttpServletResponse res) {
        try {
            return res.getWriter();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to get writer: " + res, e);
        }
    }

    private LinerleCallbacks() {
        throw new IllegalStateException("Don't make me!");
    }
}
