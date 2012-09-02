package vkode.linerle;

import java.util.ArrayList;
import java.util.List;

public class Books {
    
    private Author[] authors = {
        new Author().setFirstName("David").setMiddleName("Foster").setLastName("Wallace")
            .setBirthDate(new Year().setYear(1963)),
        new Author().setFirstName("Thomas").setLastName("Pynchon")
            .setBirthDate(new Year().setYear(1940)),
        new Author().setFirstName("Umberto").setLastName("Echo")
            .setBirthDate(new Year().setYear(1935))
    };
    
    private Book[] books = {
        new Book().setName("V").setAuthor(authors[1]),
        new Book().setName("Infinite Jest").setAuthor(authors[0]),
        new Book().setName("The Name of the Rose").setAuthor(authors[2])
    };
    

    public Book[] getBooks(String authorName) {
        List<Book> books = new ArrayList<Book>();
        for (Book book : this.books) {
            Author author = book.getAuthor();
            if (author.getFirstName() != null && author.getFirstName().contains(authorName) ||
                author.getMiddleName() != null && author.getMiddleName().contains(authorName) ||
                author.getLastName() != null && author.getLastName().contains(authorName)) {
                books.add(book);
            }
        }
        return books.toArray(new Book[books.size()]);
    } 
}
