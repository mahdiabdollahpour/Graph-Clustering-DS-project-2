package app;

import java.util.ArrayList;

public class MyQueue<T> {


    private ArrayList<T> arr = new ArrayList<>();

    public void add(T elemnet) {
        arr.add(elemnet);
    }

    public T dequeue() {
        return arr.remove(0);
    }

    public boolean isEmpty() {
        return arr.isEmpty();
    }

}
