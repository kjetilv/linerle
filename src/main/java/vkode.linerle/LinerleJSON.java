package vkode.linerle;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

final class LinerleJSON {

    private static final ObjectMapper objectMapper;

    static {
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z"));
        objectMapper = om;
    }

    @SuppressWarnings("unchecked")
    static Object[] inputValues(HttpServletRequest req, Op<?> callback) {
        return values(callback.getTypes(), callback.getArity(),
                      (Map<String, String[]>) req.getParameterMap());
    }

    static void write(Object value, PrintWriter writer) {
        try {
            objectMapper.writeValue(writer, value);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write value " + value + " to " + writer, e);
        }
    }

    static Object[] values(Class<?>[] types, int arity, Map<String, String[]> parameterMap) {
        Object[] values = new Object[arity];
        for (int i = 0; i < arity; i++) {
            String[] strings = parameterMap.get("arg" + i);
            values[i] = convert(types[i], strings[i]);
        }
        return values;
    }

    private static Object convert(Class<?> type, String string) {
        if (type.getPackage() != null && type.getPackage().getName().startsWith("vkode")) {
            try {
                return objectMapper.readValue(string, type);
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to read into " + type + ": " + string, e);
            }
        }
        return objectMapper.convertValue(string, type);
    }

    private LinerleJSON() {
        throw new IllegalStateException("Don't make me");
    }
}
