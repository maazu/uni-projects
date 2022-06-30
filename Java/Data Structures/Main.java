package Ex1;

public class Main {
    public static void main(String[] args) {

        PriorityQueue PQueue = new PriorityQueue();
        PQueue.addtopq("hello", 1);
        PQueue.addtopq("hi", 5);
        System.out.println("\n" + "Queue contents: " +  PQueue);

        //  Adding "ho" by calling addToPq method
        System.out.println("Adding \" "+"ho"+"\" with priority 3");
        PQueue.addtopq("ho", 3);

        // Printing all data of the Queue
        System.out.println("Queue Contents: " +  PQueue);

        // Calling the Priority Number Of Front Element
        System.out.println("Calling frontpri: " +  PQueue.frontPri() + " returned");

        // Calling the data which is on the front
        System.out.println("Calling front: \"" + PQueue.front() + "\" returned");

        //  Calling Remove Front Method & after removal data
        System.out.println("Calling removefront");
        PQueue.removeFront();

        System.out.println("Queue Contents After Removal : " +  PQueue);
        System.out.println("Calling isempty: " +  PQueue.isEmpty() + " returned" + "\n");


  	/* Test the behaviour of Methods
         *  front, frontPri, and removeFront
         *  also testing  the behaviour of the addtopq
         *  range greater 20
         * using try and Catch block
         */


        PriorityQueue Test_behaviour_Q = new PriorityQueue();

        // Testing Behaviour to take item from front
        try {
            Test_behaviour_Q.front();
        } catch (QueueException exception){
            System.out.println(exception);
        }


        // Testing Behaviour to take item Priority Number from front
        try {
            Test_behaviour_Q .frontPri();
        } catch (QueueException  exception){
            System.out.println(exception);
        }



        // Testing Behaviour to remove an item
        try {
            Test_behaviour_Q .removeFront();
        } catch (QueueException exception){
            System.out.println(exception);
        }



        // Testing Behaviour to remove an item with higher range Greater then 20
        try {
            Test_behaviour_Q.addtopq("TestingNumber", 21);
        } catch (QueueException exception){
            System.out.println( exception);
        }

    }
}

