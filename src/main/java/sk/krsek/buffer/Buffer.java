package sk.krsek.buffer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import sk.krsek.dto.Command;

public class Buffer {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private final BlockingQueue<Command> queue;
  private final int size;

  public Buffer(int size) {
    this.queue = new LinkedBlockingQueue<>();
    this.size = size;
  }

  public synchronized void put(Command command) {
    while (queue.size() == size) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    try {
      // ignore command waiting for over specified time for queue to be available
      boolean success = queue.offer(command, 10, TimeUnit.MINUTES);
      if (success) {
        notifyAll();
      } else {
        logger.severe(
            "Elapsed waiting time for queue to be available. Ignoring command: " + command.getType() + " command");
      }
    } catch (InterruptedException e) {
      logger.severe("Thread was interrupted while waiting for queue to be available");
    }
  }

  public synchronized Command get() {
    while (queue.isEmpty()) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    Command commandAbstraction = queue.poll();
    notifyAll();
    return commandAbstraction;
  }

  public int getSize() {
    return queue.size();
  }
}
