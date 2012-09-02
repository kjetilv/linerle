package vkode.linerle;

import java.util.Objects;
import java.util.UUID;

public final class CallId<T> {

    private final String url;

    private final T target;

    private final UUID id;

    public CallId(T target) {
        this(null, target);
    }
    
    public CallId(String url, T target) {
        this.url = (url == null || url.trim().isEmpty() ? "/" : url).trim();
        this.target = Objects.requireNonNull(target, "Null target");
        this.id = UUID.randomUUID();
    }

    public String getUrl() {
        return url;
    }

    public UUID getId() {
        return id;
    }
    
    public String getCallbackURL() {
        return url + (url.endsWith("/") ? "" : "/") + id + "/";
    }

    public T getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[@" + getUrl() + " => " + target + "]";
    }
}
