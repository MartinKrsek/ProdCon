package sk.krsek.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import sk.krsek.buffer.Buffer;
import sk.krsek.dto.Command;
import sk.krsek.dto.CommandType;
import sk.krsek.dto.ObjectCommand;
import sk.krsek.model.User;

public class Producer implements Runnable {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private final Buffer buffer;

  public Producer(Buffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public void run() {
    List<Command> commands = new ArrayList<>();
    commands.add(new ObjectCommand(CommandType.ADD, new User(1, "a1", "Name" + 1)));
    commands.add(new ObjectCommand(CommandType.ADD, new User(2, "a2", "Name" + 2)));
    commands.add(new ObjectCommand(CommandType.PRINT_ALL));
    commands.add(new ObjectCommand(CommandType.DELETE_ALL));
    commands.add(new ObjectCommand(CommandType.PRINT_ALL));

    for (Command command : commands) {
      buffer.put(command);
      logger.info("Produced " + command.getType() + " command");

      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    buffer.put(new Command(CommandType.STOP));
  }
}
