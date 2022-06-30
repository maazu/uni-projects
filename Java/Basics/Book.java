package Assignment;

/**
 * Created by mm17396 on 23/04/2018.
 */

public class Book extends LibraryItem  {

    // instance variables
    String name;
    String refNum;
    int year;
    String author;
    String publisher;

    // The constructor
    public Book(String name, String refNum, int year, String author, String publisher) {
        this.name = name;
        this.refNum = refNum;
        this.year = year;
        this.author =author;
        this.publisher=  publisher;
    }

    public String toString() {
        return "Book "+name + " ," + refNum+ " ," + year + " ," +author +" ,"+publisher;
    }

}


