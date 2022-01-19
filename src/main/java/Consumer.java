import java.util.ArrayDeque;

// What should I rather use - Thread or Runnable?
public class Consumer extends Thread {

    private final Buffer buffer_;

    private final ArrayDeque<Integer> consumedProducts;

    public Consumer(Buffer buffer) {
        this.buffer_ = buffer;
        consumedProducts = new ArrayDeque<Integer>();
    }

    private void readFromBufferAndLog() {
        synchronized (buffer_) {
            int consumedProduct = buffer_.pop();
            consumedProducts.add(consumedProduct);
            logChanges(consumedProduct);
        }
    }

    private void logChanges(Integer i) {
        System.out.println("[Consumer] took " + i + " from the buffer");
    }

    // If there is no this.sleep() in while-cycle is that fine to not have try-catch in method at all?
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            synchronized (buffer_) {
                if (buffer_.isEmpty()) {
                    System.out.println("[Consumer] tried to consume, but the buffer was empty");
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        this.interrupt();
                    }
                } else if (buffer_.isFull()) {
                    while (!buffer_.isEmpty()) {
                        readFromBufferAndLog();
                    }
                } else {
                    readFromBufferAndLog();
                }
            }
        }
    }

}
