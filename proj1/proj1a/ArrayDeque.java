public class ArrayDeque<T> implements Deque<T>{

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int first = nextLast(nextFirst);
        int last = nextFirst(nextLast);
        if(last < first) { // case where the array has looped back around (increase size)
            System.arraycopy(items,first, a, 0, size-first);
            System.arraycopy(items,0, a,size-first,first); // copy items from start to first
        }
        else { // case where array has not looped back around (downsize)
            System.arraycopy(items,first, a, 0, (items.length-first) - (items.length-last) + 1);
        }
        items = a;
        nextFirst = capacity-1; //
        nextLast = size;
    }

    private int nextFirst(int first) {
        // returns the value of nextFirst for a given value of first
        if(first == 0) {
            return items.length-1;
        } else {
            return first-1;
        }
    }

    private int nextLast(int last) {
        if(last == items.length-1) {
            return 0;
        } else {
            return last+1;
        }
    }

    @Override
    public void addFirst(T item) {
        // adds an item of type T to the front of the deque
        if(size == items.length) {
            resize(size*2);
        }
        items[nextFirst] = item;
        size++;
        nextFirst = nextFirst(nextFirst);
    }

    @Override
    public void addLast(T item) {
        // adds an item of type T to the back of the deque
        if (size == items.length) {
            resize(size*2);
        }
        items[nextLast] = item;
        size++;
        nextLast = nextLast(nextLast);
    }

    @Override
    public int size() {
        // returns the number of items in the deque
        return size;
    }

    @Override
    public void printDeque() {
        // prints items in the deque from first to last, separated by a space. Once all of the items have been printed, print out a new line
        for(int i = 0; i < size; i++) {
            int first = nextLast(nextFirst);
            System.out.print(items[first+i]);
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        // removes and returns the item at the front of the deque. If no such item exists, return null
        if(size == 0) {
            return null;
        } else {
            if((4 * size) < items.length) {
                resize(items.length/2); // should run some tests to see if shrinking works
            }
            int first = nextLast(nextFirst);
            T first_item = items[first];
            items[first] = null;
            nextFirst = first;
            size = size-1;
            return first_item;
        }

    }

    @Override
    public T removeLast() {
        // removes and returns the item at the back of the deque. If no such item exists, return null
        if(size == 0) {
            return null;
        } else {
            if(4*size < items.length) {
                resize(items.length/2); // should run some tests to see if shrinking works
            }
            int last = nextFirst(nextLast);
            T last_item = items[last];
            items[last] = null;
            nextLast = last;
            size = size-1;
            return last_item;
        }
    }

    @Override
    public T get(int index) {
        // gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, return null. Must not alter the deque!
        if(index >= size) {
            return null;
        } else {
            return items[index];
        }
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
        items = (T[]) new Object[other.size()*2]; // default size of the array deque object
        for(int i = 0; i < other.size(); i++) {
            items[i] = (T) other.get(i);
        }
        size = other.size();
        nextFirst = items.length; // at initialization, first entry is in position 0
        nextLast = size; // at initialization, last entry is in position size-1
    }


}