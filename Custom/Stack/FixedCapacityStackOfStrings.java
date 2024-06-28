package Custom.Stack;

public class FixedCapacityStackOfStrings {
    
    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty(){
        return N == 0;
    }
    
    public boolean isFull(){
        return N == s.length;
    }
    public void push(String item) {
        if (isFull()) {
            throw new RuntimeException("Overflow");
        } 
        s[N++] = item;
    }

    public String pop() {
        // return s[--N];   // loitering
        // now garbade collector can relaim memory with no outstanding referencing
        if (isEmpty()) {
            throw new RuntimeException("Underflow");
        }
        String item = s[--N];
        s[N] = null;
        return item;
    }
}