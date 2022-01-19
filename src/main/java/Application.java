public class Application {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Consumer consumer = new Consumer(buffer);
        Producer producer = new Producer(buffer);
        producer.start();
        consumer.start();
        Thread.currentThread().sleep(1000);
        // How to use join() correctly?
        producer.interrupt();
        consumer.interrupt();
    }
}
