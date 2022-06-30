package Ex1;
class Queue
{
   
    private int priority;  // set the item number
    private String data; // set  the the holding data
    private Queue next; // set next item in the queue

    public Queue(String data, int priority, Queue q) {
        if ((priority > 20) || (priority < 1)) {
            throw new QueueException("Invalid Number Range ");
        }
        this.data = data;
        this. priority = priority;
        next = q;
    }

    public int getPriority(){
        return priority;
    }
    public String getData() {
        return data;
    }
    public Queue getNext(){
        return next;
    }
    public void setNext(Queue next){
        this.next = next;
    }

}

class QueueException extends RuntimeException {
    QueueException(String s) {
        super(s);
    }
}


public class PriorityQueue {

    private Queue frontPos; //

    /*  Front Method
     * @return the front element of the Queue
     */

    public String front() throws QueueException {
        if (frontPos == null)
            throw new QueueException("front");
        else return frontPos.getData();
    }

    /*  Front Priority Method
     * @return the front Priority Numebr
     * of the element in  the Queue
     */
    public int frontPri() throws QueueException {
        if (frontPos == null) throw new QueueException("frontPro Exception");
        else return frontPos.getPriority();
    }

    /*  Remove From Front Method
     * remove the front element
     */

    public void  removeFront() throws QueueException {
        if (frontPos == null)  throw new QueueException("removeFront");
        else frontPos = frontPos.getNext();
    }

    /* Empty Method
     * Check wheter the Queue
     * Is Empty or not
     */

    public boolean isEmpty() {
        return frontPos == null;
    }

    /* ToString Method
     * Return the data in the < "hello": 1, "hi": 5 >
     */

    /*  addtoPq Method
     * /add an Item to the queue
     */

    public boolean addtopq(String data, int priority ) {
        if (frontPos == null) {
            frontPos = new Queue(data, priority, null);

        } else {
            Queue q = frontPos;
            while (q.getNext() != null) {
                if (q.getNext().getPriority() < priority) {
                    if (q.getPriority() < priority) {
                        q.setNext(new Queue(q.getData(), q.getPriority(), q.getNext()));
                        frontPos = new Queue(data, priority, q.getNext());
                    }
                    break;
                }
                q = q.getNext();
            } // end of loop

            q.setNext(new Queue(data, priority, q.getNext()));
        }  // else end
        return true;
    } // AddtoPq Ending





    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append('<');
        Queue fq = frontPos;
        while (fq != null) {
            sb.append(fq.getData());
            sb.append(":");
            sb.append(fq.getPriority());
            if (fq.getNext() != null)
                sb.append(", ");
            fq = fq.getNext();
        }
        return(sb + ">");
    }


} //Class Ends










