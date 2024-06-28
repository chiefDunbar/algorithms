import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size, itemCount;
    private int head, tail;
    private Item[] deque;

    // construct an empty deque
    public Deque() {
        this.size = 1;
        this.itemCount = 0;
        this.head = 0;
        this.tail = 0;
        this.deque = (Item[]) new Object[size];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return itemCount == 0;
    }

    private boolean isFull() {
        return itemCount == size;
    }

    // return the number of items on the deque
    public int size() {
        return itemCount;
    }

    private void resize(int newSize) {
        Item[] temp = (Item[]) new Object[newSize];
        for (int count = 0; count < itemCount; count++) {
            temp[count] = deque[(head + count) % this.size];
        }
        deque = temp;
        this.size = newSize;
        head = 0;
        tail = itemCount;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isFull()) {
            resize(size * 2);
        }
        head = (head - 1 + size) % size;
        deque[head] = item;
        itemCount++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (isFull()) {
            resize(size * 2);
        }
        deque[tail] = item;
        tail = (tail + 1) % size;
        itemCount++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Item item = deque[head];
        deque[head] = null;

        head = (head + 1) % size;
        itemCount--;

        if (itemCount > 0 && itemCount == size / 4) {
            resize(size / 2);
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        tail = (tail - 1 + size) % size;

        Item item = deque[tail];
        deque[tail] = null;

        itemCount--;

        if (itemCount > 0 && itemCount == size / 4) {
            resize(size / 2);
        }
        return item;
    }

    private class DequeIterator implements Iterator<Item> {
        private int counter;

        public DequeIterator() {
            counter = 0;
        }

        public boolean hasNext() {
            return counter < itemCount;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = deque[(counter++ + head) % size];
            return item;
        }

        public void remove() {

            throw new UnsupportedOperationException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        // Deque<Integer> deque = new Deque<Integer>();
        // for (int i = 0; i < 120; i++) {
        //     deque.addFirst(i);
        // }
        // for (int i = 0; i < 128; i++) {
        //     StdOut.println(deque.removeLast());
        // }
        // // for (Integer item : deque) {
        // //     System.out.println(item);
        // // }
        // StdOut.println("Size: " + deque.size);
    }

}
