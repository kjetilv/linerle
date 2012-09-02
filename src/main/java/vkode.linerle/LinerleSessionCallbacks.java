package vkode.linerle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinerleSessionCallbacks {
    
    private final Map<UUID, Op<?, ?>> ops = new HashMap<>();

    private final List<UUID> uuids = new ArrayList<>();

    private static final Pattern UUID_REGEX =
        Pattern.compile(".*([a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}).*");

    private static final int minLength = 
        (Linerle.getContextPath() + "/" + Linerle.getPrefix() + "/" + UUID.randomUUID()).length();

    public String getScript() {
        StringBuilder sb = new StringBuilder();
        for (UUID uuid : uuids) {
            Op<?, ?> op = ops.get(uuid);
            sb.append("function ").append(op.getName()).append("(");
            for (int i = 0, arity = op.getArity(); i < arity; i++) {
                sb.append("arg").append(i).append(", ");
            }
            sb.append("callback) {\n");
            sb.append("  $.getJSON('").append(Linerle.getContextPath()).append(Linerle.getPrefix()).append("/").append(op.getName()).append("/").append(uuid).append("',\n");
            sb.append("            { ");
            for (int i = 0, arity = op.getArity(); i < arity; i++) {
                sb.append("arg").append(i).append(": ").append("linerle_input_value(arg").append(i).append(")");
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

    public <T> void define(Op<T, ?> op) {
        UUID key = UUID.randomUUID();
        ops.put(key, op);
        uuids.add(key);
    }

    public boolean processed(HttpServletRequest req, HttpServletResponse res) {
        String uri = req.getRequestURI();
        int length = uri.length();
        if (uri.contains(Linerle.getPrefix()) && length > minLength) {
            Matcher matcher = UUID_REGEX.matcher(uri);
            if (matcher.matches()) {
                String uuid = matcher.group(1);
                UUID callbackUUID = UUID.fromString(uuid);
                Op<?, ?> callback = ops.get(callbackUUID);
                if (callback != null) {
                    Object[] values = LinerleJSON.inputValues(req, callback);
                    Object returnValue = LinerleExec.execute(callback, values);
                    LinerleJSON.write(returnValue, getWriter(res));
                    return true;
                }
            }
        }
        return false;
    }

    private static PrintWriter getWriter(HttpServletResponse res) {
        try {
            return res.getWriter();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to get writer: " + res, e);
        }
    }
}
