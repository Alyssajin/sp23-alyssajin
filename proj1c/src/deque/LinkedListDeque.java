package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    /** Creates a Node class and introduce one or more instance variables. */
    private class Node {
        private T item;
        private Node next;
        private Node prev;

        private Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    /** Stays at the front of the list. Every node comes after it. */
    private Node sentinel;
    private int size;

    /** Constructs an empty LinkedListDeque without using any variables. */
    public LinkedListDeque() {
        size = 0;

        sentinel = new Node((T) "25", null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Helper function for getRecursive(). Returns the ith Node. */
    public Node getNodeRecursive(int index) {
        Node p;
        if (index == 0) {
            p = sentinel.next;
            return p;
        }
        p = getNodeRecursive(index - 1).next;
        return p;
    }
    @Override
    public void addFirst(T x) {
        size += 1;

        sentinel.next = new Node(x, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
    }

    @Override
    public void addLast(T x) {
        size += 1;

        sentinel.prev = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel.next;
        while (p.next != sentinel.next) {
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removedItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return removedItem;
    }

    @Override
    public T get(int index) {
        Node p = sentinel.next;
        if (index >= size || index < 0) {
            return null;
        }
        for (int i = 0; i < index; i ++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node p = getNodeRecursive(index);
        return p.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ( o instanceof LinkedListDeque otherList) {
            if (this.size != otherList.size) {
                return false;
            }
            for (T x: this) {
                if (!otherList.toList().contains(x)) {
                    return false;
                }
            }
        }
        return true;
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        Node wizPos;
        public LinkedListDequeIterator() {
            wizPos = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            if (wizPos == sentinel) {
            return false;
            }
            return true;
        }

        @Override
        public T next() {
            T returnedItem = wizPos.item;
            wizPos = wizPos.next;
            return returnedItem;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        for (String s : lld1) {
            System.out.println(s);
        }
    }
}
