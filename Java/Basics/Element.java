package Assignment;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Element{

    private int number;
    private String symbol;
    private String name;
    private int group;
    private int period;
    private double weight;






    public Element(int number, String symbol,String name, int group,int period,double weight) {
        super();
        this.number = number;
        this.symbol = symbol;
        this.name = name;
        this.group = group;
        this.period = period;
        this.weight = weight;
    }

    public int getnumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public int getGroup() {
        return group;
    }
    public void setSalary(int group) {
        this.group = group;
    }

    public int getPeriod() {
        return period;
    }
    public void setPeriod(int period) {
        this.group = group;
    }


    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }





    public String toString() {
        return number + "," + symbol+ "," + name + "," +group +","+period+","+weight;
    }


    public static List<Element> readElements() throws FileNotFoundException {

        final String file = "M:\\elements.csv";
        Scanner input = new Scanner(new File(file));
        List<Element> elements = new ArrayList<>();

        String DELIMITER = ",";

        if (input.hasNextLine()) {
            input.nextLine();
        }


        while (input.hasNextLine()) {
            //separatoor
            String[] fields = input.nextLine().split(DELIMITER);

            // tur
            int number = Integer.parseInt(fields[0]);
            String symbol = fields[1];
            String name = fields[2];
            int group = Integer.parseInt(fields[3]);
            int period = Integer.parseInt(fields[4]);
            double weight = Double.parseDouble(fields[5]);


            Element element = new Element(number, symbol, name, group, period, weight) {


                public int compareTo(Element o) {
                    return 0;
                }
            };

            elements.add(element);
        }
        return elements;
    }

}
