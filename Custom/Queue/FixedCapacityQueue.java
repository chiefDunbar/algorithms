package Custom.Queue;

public class FixedCapacityQueue<Item> {
    private Item[] queue;
    private int head, tail, capacity, n;

    // there's an alterate implementation for tracking empty full using just head and tail
    FixedCapacityQueue(int capacity) {
        // this.capacity = capacity + 1;
        this.capacity = capacity;
        // queue = (Item[]) new Object[this.capacity];
        queue = (Item[]) new Object[capacity];
        head = 0;
        tail = 0;
        n = 0;
    }

    public boolean isEmpty(){
        // return head == tail;
        return n == 0;
    }

    public boolean isFull(){
        // return (tail + 1) % capacity == head;
        return n == capacity;
    }

    public void enqueue(Item item) {
        if (isFull()) {
            throw new RuntimeException("Queue Overflow");
        }
        queue[tail] = item;
        tail = (tail + 1) % capacity;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow");
        }
        Item item = queue[head];
        queue[head] = null;         // Avoid loitering
        head = (head + 1) % capacity;
        n--;
        return item;
    }

}
