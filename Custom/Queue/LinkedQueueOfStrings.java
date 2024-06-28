package Custom.Queue;

public class LinkedQueueOfStrings {
    private class Node {
        String item;
        Node next;
    }

    private Node first, last;

    // public LinkedQueue(){
    //     first.next = last;
    //     last.next = null;
    // }

    public void enqueue(String item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) 
            first = last;
        else 
            oldlast.next = last;
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
