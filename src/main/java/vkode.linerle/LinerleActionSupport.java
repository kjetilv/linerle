package vkode.linerle;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class LinerleActionSupport extends ActionSupport {

    private static final long serialVersionUID = -5246404981946306415L;

    private final LinerleSessionCallbacks callbacks;

    protected <T> void define(Op<?> op) {
        callbacks.define(op);
    }
    
    public String getScript() {
        return callbacks.getScript();
    }

    protected LinerleActionSupport() {
        this.callbacks = new LinerleSessionCallbacks();
        ActionContext.getContext().getSession().put("linerleActions", this.callbacks);
    }
}
