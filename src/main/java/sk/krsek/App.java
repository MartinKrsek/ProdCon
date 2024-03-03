package sk.krsek;

import sk.krsek.buffer.Buffer;
import sk.krsek.runner.Consumer;
import sk.krsek.runner.Producer;

/**
 *
 * Commands are defined inside Producer.run() method. Now called commands are:
 * <pre>
 * Add (1, &quot;a1&quot;, &quot;Robert&quot;)
 * Add (2, &quot;a2&quot;, &quot;Martin&quot;)
 * PrintAll
 * DeleteAll
 * PrintAll
 * </pre>
 */
public class App {

  public static void main(String[] args) {
    Buffer buffer = new Buffer(10);

    Thread producerThread = new Thread(new Producer(buffer));
    Thread consumerThread = new Thread(new Consumer(buffer));

    producerThread.setName("Producer");
    consumerThread.setName("Consumer");
    producerThread.start();
    consumerThread.start();
  }
}