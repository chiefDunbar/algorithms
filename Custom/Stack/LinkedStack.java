package Custom.Stack;

public class LinkedStack<Item> {
    private Node first = null;

    private class Node {
        Item item;
        Node next;

        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        first =  new Node(item, first);
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        return item;
    }
    
}