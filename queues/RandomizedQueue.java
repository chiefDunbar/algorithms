import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int head, tail;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.size = 0;
        this.head = 0;
        this.tail = 0;
        this.queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private boolean isFull() {
        return queue.length == size;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int count = 0; count < size; count++) {
            temp[count] = queue[(count + head) % queue.length];
        }
        queue = temp;
        head = 0;
        tail = size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (isFull())
            resize(queue.length * 2);

        queue[tail] = item;
        tail = (tail + 1) % queue.length;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        int randomIndex = (head + StdRandom.uniformInt(size)) % queue.length;
        Item item = queue[randomIndex];
        queue[randomIndex] = queue[head];
        queue[head] = null;
        head = (head + 1) % queue.length;
        size--;

        if (size > 0 && size == queue.length / 4)
            resize(queue.length / 2);

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        return queue[(head + StdRandom.uniformInt(size)) % queue.length];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int counter;
        private int[] order;

        RandomizedQueueIterator() {
            counter = 0;
            order = new int[size];
            for (int i = 0; i < size; i++) {
                order[i] = i;
            }
            StdRandom.shuffle(order);
        }

        public boolean hasNext() {
            return counter < size;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return queue[(head + order[counter++]) % queue.length];
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        // int n = 5;
        // RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        // for (int i = 0; i < n; i++)
        //     queue.enqueue(i);
        // for (int a : queue) {
        //     for (int b : queue)
        //         StdOut.print(a + "-" + b + " ");
        //     StdOut.println();
        // }
    }

}
