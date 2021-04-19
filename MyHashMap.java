/*VOLODYMYR DINUL
 * GOIT
 * Housaufgaben #8
 * Aufgabe #5
 * HashMap
 * */

package main.java.ua.goit.hw8;

import java.util.LinkedList;
import java.util.Objects;

public class MyHashMap {
    /*we will be using an array of linked lists.
    * The lise of the hash table is initially set to 2.  This is an arbitrary figure.
    * The map will resize, but the smaller it is set at the beginning, the
    * faster it will resize*/
    LinkedList<Entry>[] hashMap = new LinkedList[2];
    int size = 0;  // the size of the hash map
    public MyHashMap() {};

    public void put(Key key, Value value) {

        if(size >= hashMap.length) {
            resize();
        }
        /*getIndex(key) is the hash code the the key.
        * By making a modulus operation on the hash key we get a valid index in the array*/
        int ix = getIndex(key) % hashMap.length;


        /*When the LL is instantiated all of the cells are null
        * if the value is null it means that nothing is there
        * Accordingly, we instantiate a new LL so that it's not null any more.
        * And then an entry is added to the LL*/
        if(hashMap[ix] == null) {
            hashMap[ix] = new LinkedList<>();
            hashMap[ix].add(new Entry(key, value));
            size++;
            return;
        }
        // the case the place in the hashmap is not null i.e. there is a collision
        else {
            for(Entry entry : hashMap[ix]) {
                if(entry.key.equals(key)) { // we check whether there is or is not a key with the same thing
                    entry.value = value;
                    size++;
                    return;
                }
            }
            /* when the program is out of the look it means that there is no an existing key that matches
            The value is then added to the end of the list
             */
            hashMap[ix].add(new Entry(key, value));
            size++;
            return;
        }
    }

    // the method gets a value if there is a value
    public Value get(Key key) {
        int ix = getIndex(key) % hashMap.length; // first it gets the index

        if(hashMap[ix] == null) return null; // there is nothing there

        // loop through the values in the position.
        // if the key is found its corresponding value is returned
        for(Entry entry : hashMap[ix]) {
            if(entry.key.equals(key)) {
                return entry.value;
            }
        }
        // if the key is not found null is returned
        return null;
    }

    public void remove (Key key) {
        if(key == null) return;

        int ix = getIndex(key) % hashMap.length;
        // if the index is null it means there is nothing to remove
        if(hashMap[ix] == null) return;

        //toRemove will store the value we will remove.  We preset it to null
        Entry toRemove = null;

        for(Entry entry: hashMap[ix]) {
            if(entry.key.equals(key)) {
                toRemove = entry;
                break;
            }
        }

        if(toRemove == null) return;

        hashMap[ix].remove(toRemove);
        size--;
    }

    public void resize() {
        LinkedList<Entry>[] oldHashMap = hashMap;
        hashMap = new LinkedList[size * 2];
        size = 0;
        /* oldHashMap is a kind of temporary variable.  When we declare the new LL, we will clear whatever
        * was stored in the old LL.  */
        for(int i = 0; i < oldHashMap.length; i++ ) {
            if(oldHashMap[i] == null) continue;
            for(Entry entry: oldHashMap[i]) {
                put(entry.key, entry.value);
            }
        }
    }
    // the method that returns the hash key
    public int getIndex(Key key) {
        return key.hashCode();
    }

    public int size() {
        return size;
    }
}

class Key {
    private int key;  // for simplicity the key is an int

    public Key(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key1 = (Key) o;
        return key == key1.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}

class Value {
    private int value;

    public Value(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Value)) return false;
        Value value1 = (Value) o;
        return value == value1.value;
    }
}

class Entry {
    /*key and value are declared public to avoid getting into getters and setters*/
    public Key key;
    public Value value;

    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}

class Principal_HM {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(new Key(10), new Value(20));
        map.put(new Key(50), new Value(200));
        map.put(new Key(30), new Value(4000));
        map.put(new Key(15), new Value(5000));

        System.out.println(map.get(new Key(50)).getValue());
    }
}