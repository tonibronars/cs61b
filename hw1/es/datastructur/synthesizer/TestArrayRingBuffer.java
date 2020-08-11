package es.datastructur.synthesizer;
import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 *  Taken from generous student on piazza!
 */

public class TestArrayRingBuffer {
    @Test
    public void testArrayRB() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(4);
        assertEquals(4, arb.capacity());
        assertEquals(0, arb.fillCount());

        arb.enqueue("one");
        arb.enqueue("two");
        arb.enqueue("three");
        arb.enqueue("four");
        assertEquals(4, arb.capacity());
        assertEquals(4, arb.fillCount());

        assertEquals("one", arb.dequeue());
        assertEquals("two", arb.dequeue());
        assertEquals("three", arb.dequeue());
        assertEquals("four", arb.peek());
        assertEquals("four", arb.dequeue());
    }

    @Test
    public void testARBIterator() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(4);
        arb.enqueue("one");
        arb.enqueue("two");
        arb.enqueue("three");
        arb.enqueue("four");

        Iterator<String> arbi = arb.iterator();
        assertTrue(arbi.hasNext());
        assertEquals("one", arbi.next());
        assertTrue(arbi.hasNext());
        assertEquals("two", arbi.next());
        assertTrue(arbi.hasNext());
        assertEquals("three", arbi.next());
        assertTrue(arbi.hasNext());
        assertEquals("four", arbi.next());
        assertFalse(arbi.hasNext());
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<String> arb1 = new ArrayRingBuffer<>(3);
        assertFalse(arb1.equals(null)); //null check
        assertFalse(arb1.equals("hi")); //class check

        ArrayRingBuffer<String> arb2 = new ArrayRingBuffer<>(3);
        ArrayRingBuffer<String> arb3 = new ArrayRingBuffer<>(2);
        assertTrue(arb1.equals(arb2)); //two empty queues equal

        ArrayRingBuffer<String> orig1 = arb1;
        ArrayRingBuffer<String> orig2 = arb2;

        arb1.enqueue("one");
        arb2.enqueue("two");
        assertFalse(arb1.equals(arb2)); //general not equals check

        arb2.dequeue();
        arb2.enqueue("one");
        assertTrue(arb1.equals(arb2)); //general equals check

        arb1.enqueue("two");
        arb2.enqueue("two");
        assertTrue(arb1.equals(arb2)); //general equals check (length > 1)

        arb2.enqueue("three");
        assertFalse(arb1.equals(arb2)); //different lengths check

        arb1.enqueue("two");
        assertFalse(arb1.equals(arb2)); //general not equals check (length > 1)

        assertSame(orig1, arb1); //test non-destructive
        assertSame(orig2, arb2); //test non-destructive
    }
}
