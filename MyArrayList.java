/*VOLODYMYR DINUL
 * GOIT
 * Housaufgaben #8
 * Aufgabe #1
 * ArrayList
 * */

package main.java.ua.goit.hw8;

import java.util.Arrays;

public class MyArrayList<E> {
    private Object[] elements;
    final static int DEFAULT_SIZE = 10;
    private int size = 0;

    // constructor sets the initial size of the ArrayList ot the default size of 10
    public MyArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    // the add method
    public void add(E e) {
        if(size == elements.length)
            ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }

    //the get method returns the value stored in the ArrayList at index i
    public E get(int i) {
        if(i >= size || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index: " + i + ", Size " + elements.length);
        }
        return (E) elements[i];
    }

    /*removes the element at index (index)
     * shifts the elements to the right of i to the left*/
    public E remove(int index) {
        if(index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size " + elements.length);
        }
        Object valorViejo = elements[index];

        int movePosition = size - index - 1;
        if (movePosition > 0) {
            System.arraycopy(elements, index + 1, elements, index, movePosition);
        }
        elements[--size] = null;
        return (E) valorViejo;
    }

    // returns the number of elements stored in the ArrayList provided that
    // the value of the element is not null
    public int size() {
        int counter = 0;
        for(Object element: elements) {
            if(element != null) counter++;
        }
        return counter;
    }

    // sets values of all the elements of the array to null
    public void clear() {
        int reihe = size();
        for(int i = 0; i < reihe; i++) {
            elements[i] = null;
        }
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}

class ArrayListPrincipal {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Word");
        list.add("Word2");
        list.add("Word3");
        list.add("Word4");
        list.add("Word5");
        list.add("Word6");
        list.add("Word7");
        list.add("Word8");
        list.add("Word8");
        list.add("Word9");
        list.add("Word0");
        list.add("Word12");
        System.out.println(list.toString());

        System.out.println(list.get(3));
        System.out.println("The size of the array is " + (list.size()));
        System.out.println(list.remove(3));
        System.out.println(list.toString());
        list.clear();
        System.out.println("the arrayList after the clearance is \n" + list.toString());
    }
}
