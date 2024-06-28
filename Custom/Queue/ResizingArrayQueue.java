package Custom.Queue;

public class ResizingArrayQueue <Item> {
    private Item queue[];
    private int head, tail, n;

    ResizingArrayQueue() {
        this.queue = (Item[]) new Object[1];
        this.head = 0;
        this.tail = 0;
        this.n = 0;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public boolean isFull() {
        return n == queue.length;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = queue[(head + i) % queue.length];
        }
        queue = temp;
        head = 0;
        tail = n;
    }

    public void enqueue (Item item){
        if (isFull()) {
            resize(queue.length * 2);
        }
        queue[tail] = item;
        tail = (tail + 1) % queue.length;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Underflow");
        }
        Item item = queue[head];
        head = (head + 1) % queue.length;
        n--;
        if (n > 0 && n == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    } 

    public Item head() {
        if (isEmpty()){
            throw new RuntimeException("Underflow");
        }
        return queue[head];
    }

    public Item tail() {
        if (isEmpty()) {
            throw new RuntimeException("Underflow");
        }
        return queue[(tail  + queue.length - 1) % queue.length];
    }
}
