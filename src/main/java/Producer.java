import java.util.Random;

public class Producer extends Thread {
    private final Buffer buffer_;

    private final Random rnd = new Random();

    public Producer(Buffer buffer) {
        this.buffer_ = buffer;
    }

    // Name - ?
    private void addToBufferAndLog() {
        synchronized (buffer_) {
            Integer nextProduct = 500 + rnd.nextInt() % 500;
            buffer_.push(nextProduct);
            logChanges(nextProduct);
        }
    }

    private void logChanges(int addedElement) {
        System.out.println("[Producer] added " + addedElement + " to the buffer");
    }

    @Override
    public void run() {
        while (!interrupted()) {
            if (buffer_.isFull()) {
                System.out.println("[Producer] tried to produce product, but the buffer is full");
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    interrupt();
                }
            } else if (buffer_.isEmpty()) {
                while (!buffer_.isFull()) {
                    addToBufferAndLog();
                }
            } else {
                addToBufferAndLog();
            }
        }
    }
}
