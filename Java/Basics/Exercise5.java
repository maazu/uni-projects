
package Assignment;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Created by mm17396 on 23/04/2018.
 */

public class Exercise5 {
    public static void main(String[] args) {
        ArrayList<LibraryItem> items = new ArrayList<>();
        Collections.addAll(items,
                new Book("Python for Arachnophobes", "E0001113", 2003, "Spider,A.", "Cashin Press"),
                new Periodical("Tarantula Monthly", "C0090210", 2010, 35),
                new Periodical("Tarantula Monthly", "D0090211", 2011, 43),
                new Book("Java for Arachnophobes", "B0001099", 2003, "Spider,A.", "Cashin Press"),
                new Periodical("Arachnids", "A0010098", 1898, 27));
        for (LibraryItem item : items){

            System.out.println(item);
        }
    }
}