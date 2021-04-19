/*VOLODYMYR DINUL
 * GOIT
 * Housaufgaben #8
 * Aufgabe #4
 * MyStack
 * */

package main.java.ua.goit.hw8;

interface Aufgabe_8_4_Stack {

/*Задание 4 - Stack#
Написать свой класс MyStack как аналог классу Stack, который работает по принципу LIFO (last-in-first-out).
Можно делать либо с помощью Node либо с помощью массива. */

    void push(Object value); // добавляет элемент в конец
    Object remove(int index); // удаляет элемент под индексом
    void clear(); // очищает коллекцию
    int size(); // возвращает размер коллекции
    Object peek(); // возвращает первый элемент в стеке (LIFO)
    Object pop(); // возвращает первый элемент в стеке и удаляет его из коллекции
}

// the stack is implemented based on the Linked List methodology
class SNode {
    Object data;
    SNode next;

    // constructor to create a new linked list node
    public SNode(Object data) {
        this.data = data;
        next = null;
    }
}

public class MyStack<T> implements Aufgabe_8_4_Stack {
    SNode front;
    SNode rear;

    public MyStack() {
        this.front = null;
        this.rear = null;
    }

    @Override
    public void push(Object value) {
        // create a new LL node
        SNode temp = new SNode(value);

        /* the stack is empty when the rear points to null
         * In this case we make both front and rear point to the newly created node,
         * which is temp */
        if(rear == null && front == null) {
            front = rear = temp;
            return;
        }

        /* General case.  Add the newly created node to the end of the queue*/
        rear.next = temp;
        rear = temp;
    }

    @Override
    public Object remove(int index) {
        // check whether the stack is empty
        if(front == null) {
            System.out.println("There is nothing in the queue, it's empty");
            return null;
        }

        // check whether the index provided is within the bounds of the existing stack
        int laenge = size();
        if(index < 0 || index >= laenge) {
            System.out.println("The index provided does not fit within the limits of the queue.");
            return null;
        }

        SNode temp = front;

        // Border case one.  The node to be removed is the very first node
        if (index == 0) {
            front = temp.next;
            return temp.data;
        }
        // Border case two.  The node to be removed is the very last node
        if(index == size()) {
            pop();
        }

        /* General case
         * iterate through the LL to reach the required element
         * We run this cycle till (index-1) as we want to stop at the node
         * immediately preceding the node to be deleted */
        for(int i = 0; temp != null && i < index - 1; i++)
            temp = temp.next;

        SNode returnNode = temp;  //we have to return the value stored in the node to be removed
        // since temp will be used for other purposes,
        // we have to store the return value somewhere

        // in next we store a link to the node following to the one to be deleted
        SNode next = temp.next.next;

        //the node to be deleted gets disconnected from the LL
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
            System.out.println("There is nothing in the stack, it's empty.");
            return 0;
        }

        SNode currentNode = front;
        int zaehler = 0; // variable to store the number of nodes in the queue
        while(currentNode != null) {
            zaehler++;
            currentNode = currentNode.next;
        }
        return zaehler;
    }

    // возвращает первый элемент в стеке (LIFO)
    // I have to assume that "первый элемент в стеке (LIFO)" means the last node in the linked list
    @Override
    public Object peek() {
        if(front == null) {
            System.out.println("There is nothing in the stack, it's empty.");
            return 0;
        }
        return rear.data;
    }

    // возвращает первый элемент в стеке и удаляет его из коллекции
    // similar to the peek() story above assume that "первый элемент в стеке" is the last node in the LL
    @Override
    public Object pop() {
        if(front == null) {
            System.out.println("There is nothing in the stack, it's empty.");
            return 0;
        }

        int laenge = size();
        SNode temp = front;
        /*we need the node which immediately precedes the last node.
        * even though we know the rear node we cannot crawl through the list backwards
        * in other words we have to start at the front and move towards the end */
        for (int i = 0; i < laenge - 2; i++)
            temp = temp.next;  //the cycle stops at the node preceding the last one

        // temp is the node before the last.  temp.next is the last node
        SNode returnNode = temp.next;

        rear = temp;
        rear.next = null;

        return returnNode.data;
    }

    // this method returns the value stored at the node of the index
    public Object get(int index) {
        if(front == null) {
            System.out.println("There is nothing in the stack, it's empty.");
            return 0;
        }

        SNode temp = front;
        for( int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }
}

class Principal_Stack {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        System.out.println("The stack has " + stack.size() + " elements.");
        for (int i = 0; i < stack.size(); i++ )
            System.out.println("The value at " + (i+1) + "th position is " + stack.get(i));

        System.out.println();
        System.out.println("The value removed by calling the pop() method is " + stack.pop());

        System.out.println();
        System.out.println("This is the result of calling the peek() method " + stack.peek());

        System.out.println();
        System.out.println(stack.remove(stack.size()-1));
        System.out.println("The stack has " + stack.size() + " elements.");
        for (int i = 0; i < stack.size(); i++ )
            System.out.println("The value at " + (i+1) + "th position is " + stack.get(i));
    }
}
