package Assignment;

import java.io.FileNotFoundException;
import java.util.*;
public class Exercise4
{
    public static void exercise4a() {
        Element oxygen = new Element(8, "O", "Oxygen", 16, 2, 15.999);
        System.out.println(oxygen);

    }

    public static void exercise4b() throws FileNotFoundException {

        List<Element> elements = Element.readElements();

        for(int Ele_num = 0;Ele_num < 20; Ele_num++)
            System.out.println(elements.get((Ele_num)));
    }

    public static void main(String[] args) throws FileNotFoundException {

        exercise4a();
        exercise4b();

    }

}

