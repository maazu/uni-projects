package Assignment;

import java.util.Arrays;
public class Exercise2 {


    public static void main(String[] args) {
        // Enter array values
        int[][] array = {{3,-1,4,0},
                {5,9,-2,6},
                {5,3,7,-8}
        };


        System.out.println("\nSum of all elements is " + sum(array));
        columnSum(array);
        rowSums(array);
        maxRowAbsSum(array);
    }




    public static int sum(int[][] array) {
        int total = 0;
        for (int i = 0; i <array.length;i++) {   // i refers to row
            for (int j = 0; j <array[i].length; j++) { // i refers to column
                total += array[i][j];
            }
        }
        return total;
    }

    //Part-B
    public static int[] rowSums(int[][] array){
        int rowNum = 0;
        int temp[] = new int[array[rowNum].length];
        int[] colSum =new int[array[0].length];

        for (int i = 0; i < array.length; i++){
            int  sum=0;
            for (int j = 0; j < array[i].length; j++){
                sum += array[i][j];
                colSum[j] += array[i][j];
            }
            temp[rowNum] = sum;
            System.out.println("Row is: " + rowNum + " Sum is: " + sum);
            rowNum++;

        }
        return colSum;
    }

    //Part-C
    public static int[] columnSum(int [][] array){

        int ColNum = 0;
        int temp[] = new int[array[ColNum].length];
        for (int i = 0; i < array[0].length; i++) {
            int sum = 0;
            for (int j = 0; j < array.length; j++) {
                sum += array[j][i];
            }
            temp[ColNum] = sum;
            System.out.println("Column is: " + ColNum + " Sum is: " + sum);
            ColNum++;
        }
        return temp;
    }



    public static int[] maxRowAbsSum(int[][] array){
        int index = 0;
        int temp[] = new int[array[index].length];
        int[] colSum =new int[array[0].length];

        for (int i = 0; i < array.length; i++){
            int  sum=0;
            for (int j = 0; j < array[i].length; j++){
                sum += Math.abs(array[i][j]);
                colSum[j] += array[i][j];
            }
            temp[index] = sum;

            index++;

        }
        int max = Arrays.stream(temp).max().getAsInt();
        System.out.println("The Maximum Absolute value of all rows = " + max);
        return colSum;
    }


}





