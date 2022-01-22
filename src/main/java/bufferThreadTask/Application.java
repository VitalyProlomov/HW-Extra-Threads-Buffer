package bufferThreadTask;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
        Thread.sleep(100);

        producer.interrupt();
        consumer.interrupt();

        producer.join();
        consumer.join();
    }
}
