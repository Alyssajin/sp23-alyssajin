import java.nio.charset.IllegalCharsetNameException;
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
                /* if items.length = 8, nextFirst = 7, nextLast = 0:
                nextFirst = the last index of the new item list.
                nextLast = 0. */
            if (nextFirst == items.length - 1 && nextLast == 0) {
                nextFirst = items.length - 1;
                /* if nextLast = 0, copy from the start index till the end of the list.
                nextFirst = nextFirst
                nextLast = the last index + 1 of the original item list. */
            } else if (nextLast == 0) {
                for (int i = nextFirst + 1; i < items.length; i++) {
                    copiedItemsList[i] = items[i];
                }
                nextLast = items.length;
                /* if nextFirst = items.length - 1, copy from the 0th item till the nextLast - 1 th item.
                nextFirst = the last index of the new item list.
                nextLast = nextLast. */
            } else if (nextFirst == items.length - 1) {
                for (int i = 0; i < nextLast - 1; i++) {
                    copiedItemsList[i] = items[i];
                }
                nextFirst = copiedItemsList.length - 1;
                /* if nextFirst is in front of nextLast, copy from the first till the last item.
                nextFirst = nextFirst
                nextLast = nextLast. */
            } else if (nextFirst < nextLast) {
                for (int i = nextFirst + 1; i <= nextLast - 1; i++) {
                    copiedItemsList[i] = items[i];
                }
                /* if nextFirst is after nextLast, copy from the first item till the end of the list +
                the 0th till the last item.
                nextFirst = new last index - (last index - nextFirst)
                nextLast = nextLast */
            } else if (nextFirst > nextLast) {
                for (int i = nextFirst + 1; i < size; i++) {
                    copiedItemsList[i] = items[i];
                }
                for (int i = 0; i <= nextLast - 1; i++) {
                    copiedItemsList[i] = items[i];
                }
                nextFirst = copiedItemsList.length - 1 - (items.length - 1 - nextFirst);
            }
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
