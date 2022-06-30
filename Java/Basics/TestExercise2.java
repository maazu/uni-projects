package Assignment;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;;

public class TestExercise2 {



        int[][] array = {{3,-1,4,0},
                        {5,9,-2,6},
                        {5,3,7,-8} };


    @org.junit.Test
    public void sum(){

        assertEquals(31, Exercise2.sum(array));
    }

    @org.junit.Test
    public void rowSums() {
        int[] b = new int[]{6, 18, 7};

        assertArrayEquals(b, Exercise2.rowSums(array));

    }

    @Test
    public void columnSum() {
        int[] b = new int[]{13, 11, 9, -2};
        assertArrayEquals(b, Exercise2.columnSum(array));
    }

    @org.junit.Test
    public void maxRowAbsSum() {

        assertEquals(23, Exercise2.maxRowAbsSum(array));
    }
}