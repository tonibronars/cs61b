public class LinkedListDeque<T> implements Deque<T> {
    // Node structure for T data type
    public class TNode {
        public TNode previous;
        public T item;
        public TNode next;
        public TNode(TNode p, T i, TNode n) {
            previous = p;
            item = i;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    @Override
    public void addFirst(T item) {
        TNode first = new TNode(sentinel,item,sentinel.next);
        sentinel.next.previous = first;
        sentinel.next = first;
        size++;
    }

    @Override
    public void addLast(T item) {
        TNode last = new TNode(sentinel.previous,item,sentinel);
        sentinel.previous.next = last;
        sentinel.previous = last;
        size++;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        TNode current = sentinel.next; // first real data
        while(current != sentinel){
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(size == 0){
            return null;
        } else {
            TNode first = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            size -= 1;
            return first.item;
        }
    }

    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        } else {
            TNode last = sentinel.previous;
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            size -= 1;
            return last.item;
        }
    }

    @Override
    public T get(int index) {
        if(size == 0){
            return null;
        } else {
            TNode looper = sentinel.next;
            for(int i = 0; i == index; i++){
                looper = looper.next;
            }
            return looper.item;
        }
    }

    public LinkedListDeque() {
        // Creates empty linked list deque
        sentinel = new TNode(null,null,null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        // creates a deep copy of other
        sentinel = new TNode(null,null,null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for(int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    private T getRecursiveHelper(int index, TNode current) {
        if(index >= size) {
            return null;
        }
        if(index == 0) {
            return current.item;
        }
        return getRecursiveHelper(index-1, current.next);
    }

    public T  getRecursive(int index) {
        return getRecursiveHelper(index,sentinel.next);
    }




}