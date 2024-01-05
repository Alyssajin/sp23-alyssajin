package deque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
        nextFirst = 0;
        nextLast = 1;
    }

    /** Check if the arrayDeque is full. */
    public boolean isFull() {
        return size >= items.length;
    }

    /** Copy the list. */
    public T[] copyList(boolean isSizeUp) {
        T[] copiedItemsList;
        int start = nextFirst;

        if (isSizeUp) {
            copiedItemsList = (T[]) new Object[items.length + 8];
        } else {
            copiedItemsList = (T[]) new Object[items.length / 2];
        }
        for (int i = 0; i < size; i ++) {
            if (start + 1 == items.length) {
                start = -1;
            }
            copiedItemsList[i] = items[start + 1];
            start ++;
        }
        nextFirst = copiedItemsList.length - 1;
        nextLast = size;
        return copiedItemsList;
    }

    /** Increase the size of the item list if the size is full. */
    public void resizeUp() {
        /* Only resizeUp when the list is full. */
        if (isFull()) {
            T[] copiedItemsList = copyList(true);
            items = copiedItemsList;
        }
    }

    /** Decrease the size of the array when the usage factor is < 25%. Meanwhile, if the size <= 8, stop resize down.*/
    public void resizeDown() {
        if (size < items.length * 0.25 && items.length > 8) {
            T[] copiedItemsList = copyList(false);
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
        resizeDown();
        T removed;
        if (size <= 0) {
            removed = null;
        } else if (nextFirst + 1 > items.length - 1) {
            removed = items[0];
            items[0] = null;
            nextFirst = 0;
            size --;
        } else {
            removed = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst ++;
            size --;
        }
        return removed;
    }

    @Override
    public T removeLast() {
        resizeDown();
        T removed;
        if (size <= 0) {
            removed = null;
        } else if (nextLast - 1 < 0) {
            removed = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
            size --;
        } else {
            removed = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast --;
            size --;
        }
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= items.length) {
            return null;
        }
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        return get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof ArrayDeque otherDeque) {
            if (this.size != otherDeque.size) {
                return false;
            }
            for (T x : this) {
                if (!otherDeque.toList().contains(x)) {
                    return false;
                }
            }
        }
        return true;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        public ArrayDequeIterator() {
            wizPos = 1;
        }
        @Override
        public boolean hasNext() {
            return wizPos <= size;
        }

        @Override
        public T next() {
            int returnedIndex;
            if (nextFirst + wizPos > items.length - 1) {
                returnedIndex = nextFirst + wizPos - items.length;
            } else {
                returnedIndex = nextFirst + wizPos;
            }
            T returnedItem = items[returnedIndex];
            wizPos += 1;
            return returnedItem;
        }
    }

    public static void main(String[] args) {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addFirst("a");
        ad.addLast("c");
        for (String s : ad) {
            System.out.println(s);
        }
    }
}
