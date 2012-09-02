package vkode.linerle;

import java.util.Objects;

import com.opensymphony.xwork2.ActionSupport;

public class Index extends ActionSupport {

    private static final long serialVersionUID = -6913325934811894699L;

    private Books books = new Books();
    
    public Index() {
        setBooks(new Books());
    }

    public final void setBooks(Books books) {
        this.books = Objects.requireNonNull(books, "Null books!");
        LinerleCallbacks.defineCallback(this, new AbstractOp1<Index, String, Book[]>("getBooks") {
            @Override
            public Book[] execute(String pattern) {
                return Index.this.books.getBooks(pattern);
            }
        });
    }

    @Override
    public String execute() throws Exception {
        return "ok";
    }
    
    public String getScript() {
        return LinerleCallbacks.getScript(this);
    }
    
    public String getName() {
        return "kjetil";
    }
}
