package vkode.linerle;

public class Book {
    
    private String id;
    
    private String title;

    private Author author;
    
    private Year year;
    
    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;        
    }

    public String getId() {
        return id;
    }

    public Book setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Year getYear() {
        return year;
    }

    public Book setYear(Year year) {
        this.year = year;
        return this;
    }
}
