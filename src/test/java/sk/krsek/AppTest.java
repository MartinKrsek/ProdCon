package sk.krsek;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.krsek.repository.UserRepository;

public class AppTest {

  private UserRepository userRepository;

  @BeforeEach
  public void beforeEach() {
    userRepository = new UserRepository();
  }

  @AfterEach
  public void afterEach() {
    userRepository.remove();
  }

  @Test
  public void main_shouldStartThreads() {
    App.main(null);
    assertThreadIsAlive("Producer", "Producer", Thread.currentThread().getThreadGroup());
    assertThreadIsAlive("Consumer", "Consumer", Thread.currentThread().getThreadGroup());
  }

  private void assertThreadIsAlive(String threadName, String threadType, ThreadGroup threadGroup) {
    Thread[] threads = new Thread[threadGroup.activeCount()];
    threadGroup.enumerate(threads);

    for (Thread thread : threads) {
      if (thread.getName().equals(threadName) && thread.isAlive()) {
        // Thread is alive
        return;
      }
    }
    throw new AssertionError(threadType + " thread is not alive");
  }
}
