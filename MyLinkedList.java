/*VOLODYMYR DINUL
 * GOIT
 * Housaufgaben #8
 * Aufgabe #2
 * Linked List
 * */

package main.java.ua.goit.hw8;

/*for the reason of simplicity assume that the LL will store just ints*/

class Node {
    /*every node has two components:
     * some data (LL is, after all, a structure to hold some data) and
     * a pointer to the next node*/
    Node next;
    int data;

    public Node(int data) {
        this.data = data;
        next = null;
    }
}

public class MyLinkedList {
    Node leiter; // the head pointer

    public MyLinkedList() {
        leiter = null;
    }

    public void add(int data) {
        // create a new node
        Node neuerNode = new Node(data);

        /* checke whether the head points to nowhere
         * if it does, make the head not to point to the node
         * just provided and return from the insertAtEnd method*/
        if(leiter == null) {
            leiter = new Node(data);
            return;
        }
        // for now leave the new node to point to nowhere
        neuerNode.next = null;

        /* now we will traverse the LL until we get to the end.
         * The end of the LL is when a node points to null*/
        Node currentNode = leiter;
        while(currentNode.next != null)
            currentNode = currentNode.next;

        /*afther this loop executes the current Node is the last one
         * accordingly, we make it point to the new node
         * By doing this we are appending the new node to the end of the list*/
        currentNode.next = neuerNode;
    }

    public int size() {
        if(leiter == null) {
            System.out.println("The list is empty");
            return 0;
        }

        Node currentNode = leiter;
        int zaehler = 0; // variable to store the number of nodes in the LL
        while(currentNode != null) {
            zaehler++;
            currentNode = currentNode.next;
        }
        return zaehler;
    }

    public int get(int index) {
        if(leiter == null) {
            System.out.println("The list is empty");
            return 0;
        }

        int laenge = size();

        if(index < 0 || index >= laenge) {
            System.out.println("The index you provided is outside the limits of the list.");
            return 0;
        }

        int zaehler = 0;
        Node currentNode = leiter;
        while(zaehler < index ) {
            currentNode = currentNode.next;
            zaehler++;
        }
        return currentNode.data;
    }

    /*An LL has only one identification point which is its head Node.
     * If the head node is set to point to nowhere then the LL is lost which is
     * the closest thing to cleared*/
    public void clear() {
        leiter = null;
        System.out.println("The list has been ravaged and is lost forever.");
    }

    public void remove(int index) {
        // checking if the list is empty
        if(leiter == null) {
            System.out.println("The list is empty");
            return;
        }

        // checking if the index provided is larger than the size of the list
        int laenge = size();
        if(index < 0 || index >= laenge) {
            System.out.println("The index you provided is outside the limits of the list.");
            return;
        }
        // initiate the list
        Node currentNode = leiter;

        // handling the case to for the removal of the first element
        if(index == 0) {
            leiter = currentNode.next;
            return;
        }

        // locate the node immediately preceding the node to be deleted
        for(int i = 0; currentNode != null && i < index - 1; i++)
            currentNode = currentNode.next;

        /*Node currentNode.next is the one to be deleted.
        * Store pointer to the next of node to be deleted*/
        Node next = currentNode.next.next;

        // unlink the deleted node from the list
        currentNode.next = next;
    }
}

class Principal {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        int ziffer = list.size();
        for (int i = 0; i < ziffer; i++ ){
            System.out.println(list.get(i));
        }
        list.remove(3);
        ziffer = list.size();
        for (int i = 0; i < ziffer; i++ ){
            System.out.println(list.get(i));
        }

        list.clear();
        System.out.println("Number of elements in the LL is " + list.size());
    }
}