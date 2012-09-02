package vkode.linerle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Books {
    
    private final List<Book> books = new ArrayList<>(Arrays.asList(
        new Book().setId("1").setTitle("V")
            .setAuthor(new Author().setFirstName("Thomas").setLastName("Pynchon")
                           .setBirthDate(new Year().setYear(1940))).setYear(new Year().setYear(1960)),
        new Book().setId("2").setTitle("Infinite Jest")
            .setAuthor(new Author().setFirstName("David").setMiddleName("Foster").setLastName("Wallace")
                           .setBirthDate(new Year().setYear(1963)))
            .setYear(new Year().setYear(1995)),
        new Book().setId("3").setTitle("The Name of the Rose")
            .setAuthor(new Author().setFirstName("Umberto").setLastName("Echo")
                           .setBirthDate(new Year().setYear(1935))).setYear(new Year().setYear(1984))));
    

    public Book[] getBooks(String authorName) {
        List<Book> books = new ArrayList<>();
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

    public void addBook(Book book) {
        books.add(book);
    }
}
