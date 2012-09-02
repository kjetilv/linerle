package vkode.linerle;

import java.util.Objects;

public class Index extends LinerleActionSupport {

    private static final long serialVersionUID = -6913325934811894699L;

    private Books books = new Books();
    
    public Index() {
        setBooks(new Books());
    }

    public final void setBooks(Books books) {
        this.books = Objects.requireNonNull(books, "Null books!");
        define(new AbstractOp1<Index, String, Book[]>("getBooks") {
            @Override
            public Book[] execute(String pattern) {
                return Index.this.books.getBooks(pattern);
            }
        });
        define(new AbstractOp1<Index, Book, Book>("addBook") {
            @Override
            public Book execute(Book book) {
                Index.this.books.addBook(book);
                return book;
            }
        });
    }

    @Override
    public String execute() throws Exception {
        return "ok";
    }
    
    public String getName() {
        return "kjetil";
    }
}
