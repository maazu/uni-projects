package Assignment;
import java.util.Random;
import java.util.Scanner;


/**
 * Generate two random integer numbers in the range 10 to 20 (inclusive).
 `* Ask the user to compute the product of these two numbers.
 * Check the answer entered by the user. Inform the user if the answer was correct or not. You can assume that the user input is an integer number.
 * Display the current score of correct answers and the total numbers of answers given.e.
 *
 * @author (Muhammad Maaz)
 */
public class Exercise1
{
    /**
     * Constructor for objects of class Exercise1
     */

    public static void main(String[] args){

        partA();
        partB();


    }








    public static void partA()
    {
        // initialise instance variables
        Random generator = new Random();
        Scanner keyboard = new Scanner(System.in);

        //for random numbers first and second
        int randomOne,randomTwo; //Problem for counting
        int userAnswer;
        int problem = 0;
        int right = 0;

        System.out.println("Exercise 1A ");

        while(problem <= 10){


            randomOne =generator.nextInt(10 + 1) + 10 ;
            randomTwo =generator.nextInt(10 + 1) + 10 ;

            int product = randomOne*randomTwo;


            System.out.println(randomOne+"*"+randomTwo+ " = ? ");

            userAnswer = keyboard.nextInt();



            if(userAnswer==product){
                right++;
                System.out.println("Correct answer. Score: "+ right + "(" + problem +")"+"\n");
                if( problem == 10 ){
                    System.out.println("Good-bye");
                }


            }
            else{

                System.out.println("InCorrect answer. Score: "+ right + "(" + problem +")"+"\n");
                if( problem == 10 ){
                    System.out.println("Good-bye");
                }
            }


            problem ++ ;
        }


    } // First Method Ends Here



    public static void partB()
    {
        // initialise instance variables
        Random generator = new Random();
        Scanner keyboard = new Scanner(System.in);

        //for random numbers first and second
        int randomOne,randomTwo; //Problem for counting
        int userAnswer;
        int problem = 1;
        int right = 0;

        System.out.println("Excersie 1B ");

        while (true) {
            randomOne =generator.nextInt(10 + 1) + 10 ;
            randomTwo =generator.nextInt(10 + 1) + 10 ;
            int product = randomOne*randomTwo;

            System.out.println(randomOne+"*"+randomTwo+ " = ? ");

            if (keyboard.hasNextInt()){
                if(keyboard.nextInt()==product){
                    right++;
                    System.out.println("Correct answer. Score: "+ right + "(" + problem +")"+"\n");
                }
                else{
                    System.out.println("InCorrect answer. Score: "+ right + "(" + problem +")"+"\n");
                }
                problem ++ ;
            }


            else if (keyboard.hasNext()){
                String i = keyboard.nextLine();
                if(i.equalsIgnoreCase("q")) {
                    System.out.println("Good-bye");

                    break;
                }
            }

            else
                System.out.println("Invalid Input " + keyboard.next());

        }
    }

}









