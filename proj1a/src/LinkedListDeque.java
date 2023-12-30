import java.util.ArrayList;
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
        return false;
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

    @Override
    public T getRecursive(int index) {
        return null;
    }

    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(6);
        lld.addFirst(25);
        lld.addLast(7);
        System.out.println(lld.toList());
    }
}
