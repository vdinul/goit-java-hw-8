/*VOLODYMYR DINUL
* GOIT
* Housaufgaben #8
* Aufgabe #3
* MyQueue
* */

package main.java.ua.goit.hw8;

interface Aufgabe_8_3_Queue {

/*Задание 3 - Queue#
Написать свой класс MyQueue как аналог классу Queue, который будет работать по принципу FIFO (first-in-first-out).
Можно делать либо с помощью Node либо с помощью массива.
*/

    void add(Object value); // добавляет элемент в конец
    Object remove(int index); // удаляет елемент под индексом
    void clear(); // очищает коллекцию
    int size(); // возвращает размер коллекции
    Object peek(); // возвращает первый элемент в очереди (FIFO)
    Object poll(); // возвращает первый элемент в очереди и удаляет его из коллекции
}

// the queue is implemented based on the Linked List methodology
class QNode {
    Object data;
    QNode next;

    // constructor to create a new linked list node
    public QNode(Object data) {
        this.data = data;
        next = null;
    }
}

public class MyQueue<T> implements Aufgabe_8_3_Queue {

    QNode front; // this points to the first element of the queue
    QNode rear; // this point to the last element of the queue

    public MyQueue() {
        front = null;
        rear = null;
    }

    @Override
    public void add(Object value) {
        // create a new LL node
        QNode temp = new QNode(value);

        /* the queue is empty when the rear point to null
        * In this case we make both front and rear point to the newly created node,
        * which is temp */
        if(rear == null) {
            front = rear = temp;
            return;
        }

        /* General case.  Add the newly created node to the end of the queue*/
        rear.next = temp;
        rear = temp;
    }

    @Override
    public Object remove(int index) {
        // check whether the queue is empty
        if(front == null) {
            System.out.println("There is nothing in the queue, it's empty");
            return null;
        }

        // check whether the index provided is within the bounds of the existing queue
        int laenge = size();
        if(index < 0 || index >= laenge) {
            System.out.println("The index provided does not fit within the limits of the queue.");
            return null;
        }

        QNode temp = front;

        // special case.  The node to be removed is the very first node
        if (index == 0) {
            front = temp.next;
            return temp.data;
        }

        /* iterate through the queue to reach the required element
        * We run this cycle till (index-1) as we want to stop at the node
        * immediately preceding the node to be deleted */
        for(int i = 0; temp != null && i < index - 1; i++)
            temp = temp.next;

        QNode returnNode = temp;  //we have to return the value stored in the node to be removed
                                    // since temp will be used for other purposes,
                                    // we have to store the return value somewhere

        // in next we store a link to the node following to the one to be deleted
        QNode next = temp.next.next;

        //the node to be deleted gets disconnected from the queue
        temp.next = next;

        return returnNode.data;
    }

    @Override
    public void clear() {
        front = null;
        rear = null;
    }

    @Override
    public int size() {
        if(front == null) {
            System.out.println("There is nothing in the queue, it's empty.");
            return 0;
        }

        QNode currentNode = front;
        int zaehler = 0; // variable to store the number of nodes in the queue
        while(currentNode != null) {
            zaehler++;
            currentNode = currentNode.next;
        }
        return zaehler;
    }

    @Override
    public Object peek() {
        if(front == null) {
            System.out.println("There is nothing in the queue, it's empty.");
            return 0;
        }
        return front.data;
    }

    @Override
    public Object poll() {
        if(front == null) {
            System.out.println("There is nothing in the queue, it's empty.");
            return 0;
        }
        QNode returnNode = front;
        QNode temp = front;
        front = temp.next.next;

        return returnNode.data;
    }
    // the method to get the value stored in the node under the index
    public Object get(int index) {
        if(front == null) {
            System.out.println("There is nothing in the queue, it's empty.");
            return 0;
        }

        QNode temp = front;
        for( int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }
}

class PrincipalQueue {
    public static void main(String[] args) {
        MyQueue <Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("The length of the queue is " + queue.size());
        System.out.println();
        for (int i = 0; i < queue.size(); i++)
            System.out.println("The " + (i+1) + "th element of the queue has the value of " + queue.get(i));
        System.out.println();

        System.out.println("The first node of the queue stores value " + queue.peek());

        System.out.println("The value of the element removed by the poll() method is " + queue.poll());
        System.out.println();
        System.out.println("After the call to the poll() method the queue looks like this:");
        for (int i = 0; i < queue.size(); i++)
            System.out.println("The " + (i+1) + "th element of the queue has the value of " + queue.get(i));
        System.out.println();

        queue.add(6);
        queue.add(7);

        queue.remove(3);
        System.out.println("After the call to the remove() method the queue looks like this:");
        for (int i = 0; i < queue.size(); i++)
            System.out.println("The " + (i+1) + "th element of the queue has the value of " + queue.get(i));
        System.out.println();

    }
}
