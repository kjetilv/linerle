package vkode.linerle;

import java.util.Objects;

public class Index extends LinerleActionSupport {

    private static final long serialVersionUID = -6913325934811894699L;

    private Books books = new Books();
    
    public Index() {
        setBooks(new Books());
    }

    public final void setBooks(final Books books) {
        this.books = Objects.requireNonNull(books, "Null books!");
        define(new Op1<String, Book[]>("getBooks") {
            @Override
            public Book[] execute(String pattern) {
                return books.getBooks(pattern);
            }
        });
        define(new Op1<Book, Book>("addBook") {
            @Override
            public Book execute(Book book) {
                books.addBook(book);
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
