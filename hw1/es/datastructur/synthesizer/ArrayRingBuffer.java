package es.datastructur.synthesizer;
import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Private class for turning the ArrayRingBuffer into an iterable
     */
    private class ArrayRingBufferIterator<T> implements Iterator<T> {
        private int count = 0;
        private int curr_position = first;

        @Override
        public boolean hasNext() {
            return !(count==fillCount());
        }
        @Override
        public T next() {
            T curr_item = (T) rb[curr_position]; // store the current item
            curr_position = next_index(curr_position); // advance the counter
            count += 1; // increment count
            return curr_item;
        }
    }

    /**
     * Private method for computing the next available index
     */
    private int next_index(int curr) {
        if(curr==(rb.length-1)){
            return 0;
        } else {
            return curr+=1;
        }
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if(isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = next_index(last);
        fillCount+=1;
        return;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if(isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T first_item = rb[first];
        first = next_index(first);
        fillCount -= 1;
        return first_item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if(isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        if(o==null){ return false; } // false if o is null
        if(!(o instanceof ArrayRingBuffer)) { return false; } // false if o is not an ArrayRingBuffer

        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if(other.fillCount() != this.fillCount()) { return false; } // false if objects don't have the same number of elements

        Iterator this_iter = this.iterator();
        Iterator other_iter = other.iterator();
        while(this_iter.hasNext()) {
            if(this_iter.next() != other_iter.next()) { return false; }
        }
        return true;
    }
}