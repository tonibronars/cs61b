public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int first;
    private int nextFirst;
    private int last;
    private int nextLast;

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items,first, a, 0, size-first); // copy items from first to end
        System.arraycopy(items,0, a,size-first-1,first+1); // copy items from start to first
        items = a;
        first = 0;
        nextFirst = capacity-1; // end of newly added elements
        last = size-1;
        nextLast = size; // first of newly added elements
    }

    private int nextFirst(int first) {
        // returns the value of nextFirst for a given value of first
        if(first == 0) {
            return items.length;
        } else {
            return first-1;
        }
    }

    private int nextLast(int last) {
        if(last == items.length-1) {
            return 0;
        } else {
            return last++;
        }
    }

    public void addFirst(T item) {
        // adds an item of type T to the front of the deque
        if(size == items.length) {
            resize(size*2);
        }
        items[nextFirst] = item;
        size++;
        first = nextFirst;
        nextFirst = nextFirst(first);
    }

    public void addLast(T item) {
        // adds an item of type T to the back of the deque
        if (size == items.length) {
            resize(size*2);
        }
        items[nextLast] = item;
        size++;
        last = nextLast;
        nextLast = nextLast(last);
    }

    public boolean isEmpty() {
        // returns true of the deque is empty, false otherwise
        return size == 0;
    }

    public int size() {
        // returns the number of items in the deque
        return size;
    }

    public void printDeque() {
        // prints items in the deque from first to last, separated by a space. Once all of the items have been printed, print out a new line
    }
    public T removeFirst() {
        // removes and returns the item at the front of the deque. If no such item exists, return null
    }
    public T removeLast() {
        // removes and returns the item at the back of the deque. If no such item exists, return null
    }
    public T get(int index) {
        // gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, return null. Must not alter the deque!
    }

    public ArrayDeque() {
        // creates an empty array deque
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3; // arbitrary
        nextLast = 4; // arbitrary
    }

    public ArrayDeque(ArrayDeque other) {
        // creates a deep copy of other
    }


}