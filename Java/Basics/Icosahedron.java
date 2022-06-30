package Assignment;

import java.text.DecimalFormat;

public class Icosahedron {

    private double edge;
    private int count = 0;


    public Icosahedron(double edge) {
        this.edge = edge;
    }

    private static int count() {
        int count = 0;

        return count;
    }


    public double surface() {
        double surface = 5 * Math.sqrt(3) * Math.pow(edge, 2);
        return surface;
    }

    public double Volume() {
        double Volume = 5 * (3 + Math.sqrt(5)) / 12 * Math.pow(edge, 3);
        return Volume;

    }

    public static int getCount() {
        int getCounter = count();

        return getCounter;

    }


    public String toString() {
        DecimalFormat f = new DecimalFormat("0.000");


        return "Icosahedron[edge= " + edge + " , " + " surface= " + f.format(surface()) + "," + " volume= " + f.format(Volume()) + "]";
    }

    public static void main(String[] args) {
        System.out.println("Number of Icosahedron objects created: " + getCount());
        Icosahedron[] icos = new Icosahedron[4];
        for (int i = 0; i < icos.length; i++)
            icos[i] = new Icosahedron(i + 1);
        for (int i = 0; i < icos.length; i++)
            System.out.println(icos[i]);
            System.out.println("Number of Icosahedron objects created: " + getCount());
    }

}