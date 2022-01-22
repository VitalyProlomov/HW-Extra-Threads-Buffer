package bufferThreadTask;

import java.util.Random;

public class Producer extends Thread {
    private final Buffer buffer;

    private final Random rnd = new Random();

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    private void addToBuffer() {
        Integer nextProduct = 500 + rnd.nextInt() % 500;
        buffer.push(nextProduct);
        System.out.println("[Producer] added " + nextProduct + " to the buffer");
    }

    @Override
    public void run() {
        while (!interrupted()) {
            synchronized (buffer) {
                if (buffer.isFull()) {
                    System.out.println("[Producer] tried to produce product, but the buffer is full");
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        interrupt();
                    }
                } else {
                    addToBuffer();
                }
                buffer.notifyAll();
            }
        }
    }
}
