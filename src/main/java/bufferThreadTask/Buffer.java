package bufferThreadTask;

import java.util.EmptyStackException;
import java.util.Stack;

public class Buffer {
    private final static int CAPACITY = 100;

    private final Stack<Integer> buffer;

    public Buffer() {
        buffer = new Stack<>();
    }

    public boolean isEmpty() {
        return buffer.empty();
    }

    public void push(Integer i) {
        buffer.push(i);
    }

    public Integer pop() {
        return buffer.pop();
    }

    public boolean isFull() {
        return buffer.size() == CAPACITY;
    }

}
