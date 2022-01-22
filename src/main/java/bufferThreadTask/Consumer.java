package bufferThreadTask;

import java.util.ArrayDeque;

public class Consumer extends Thread {

    private final Buffer buffer;

    private final ArrayDeque<Integer> consumedProducts;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
        consumedProducts = new ArrayDeque<Integer>();
    }

    private void readFromBuffer() {
        int consumedProduct = buffer.pop();
        consumedProducts.add(consumedProduct);
        System.out.println("[Consumer] took " + consumedProduct + " from the buffer");
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    System.out.println("[Consumer] tried to consume, but the buffer was empty");
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        this.interrupt();
                    }
                } else {
                    readFromBuffer();
                }
                buffer.notifyAll();
            }
        }
    }

}
