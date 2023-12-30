import java.nio.charset.IllegalCharsetNameException;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    /** Use AList to construct arrayDeque. */
    private class AList<Item> {
        private Item[] items;
        private int size;

        private AList() {
            items = (Item[]) new Object[6];
            size = 0;
        }
    }
    /** Index of the next front item. */
    private int nextFirst;
    /** Index of the next last item. */
    private int nextLast;
    private AList p;


    /** Constructor for an empty arrayDeque. */
    public ArrayDeque() {
        /* Initialise the nextFirst index && the nextLast index. */
        nextFirst = 2;
        nextLast = 3;
    }

    /** Check if the arrayDeque is full. */
    public boolean isFull() {
        return false;
    }

    @Override
    public void addFirst(T x) {

        nextFirst += 1;

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    public static void main(String[] args) {
        Deque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(7);
        ad.addFirst(25);
    }

}
