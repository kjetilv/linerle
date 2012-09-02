package vkode.linerle;

public class Book {
    
    private String name;

    private Author author;

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;        
    }

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }
}
