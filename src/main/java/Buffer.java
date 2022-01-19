import java.util.EmptyStackException;
import java.util.Stack;

public class Buffer {
    private final int CAPACITY = 100;

    private final Stack<Integer> buffer_;

    public Buffer() {
        buffer_ = new Stack<Integer>();
    }

    public boolean isEmpty() {
        return buffer_.empty();
    }

    public void push(Integer i) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        buffer_.push(i);
    }

    public Integer pop() throws EmptyStackException {
        return buffer_.pop();
    }

    public boolean isFull() {
        return buffer_.size() == CAPACITY;
    }

}
