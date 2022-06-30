package Assignment;

/**
 * Created by mm17396 on 23/04/2018.
 */

public class Periodical extends LibraryItem  {

    // instance variables
    String name;
    String refNum;
    int year;
    int issueNum;

    // The constructor
    public Periodical (String name, String refNum, int year, int issueNum){
        this.name = name;
        this.refNum = refNum;
        this.year = year;
        this.issueNum =issueNum;

    }

    public String toString() {
        return "Periodical "+name + " ," + refNum+ " ," + year + " ," +issueNum ;
    }

}

