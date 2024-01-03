import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {

    /** Use AList to construct arrayDeque. */
    private T[] items;
    private int size;
    /** Index of the next front item. */
    private int nextFirst;
    /** Index of the next last item. */
    private int nextLast;

    /** Constructor for an empty arrayDeque. */
    public ArrayDeque() {
        /* Initialise the nextFirst index && the nextLast index. */
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 2;
        nextLast = 3;
    }

    /** Check if the arrayDeque is full. */
    public boolean isFull() {
        return size >= items.length;
    }

    /** Increase the size of the item list if the size is full. */
    public void resizeUp() {
        /* Only resizeUp when the list is full. */
        if (isFull()) {
            T[] copiedItemsList = (T[]) new Object[items.length + 8];
            for (int i = 0; i < items.length; i ++) {
                copiedItemsList[i] = items[i];
            }
            nextFirst = copiedItemsList.length - 1;
            nextLast = items.length;
            items = copiedItemsList;
        }
    }

    @Override
    public void addFirst(T x) {
        resizeUp();
        items[nextFirst] = x;
        if (nextFirst - 1 < 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst --;
        }
        size ++;
    }

    @Override
    public void addLast(T x) {
        resizeUp();
        items[nextLast] = x;
        if (nextLast + 1 == items.length) {
            nextLast = 0;
        } else {
            nextLast ++;
        }
        size ++;
    }

    @Override
    public List<T> toList() {
        return new ArrayList<>(Arrays.asList(items));
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
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
        ad.addFirst(19);
        ad.addLast(94);
        ad.addLast(3);
        ad.addLast(9);
        ad.addFirst(93);
        ad.addFirst(6);
        ad.addFirst(2);
        ad.addLast(1);
    }

}
