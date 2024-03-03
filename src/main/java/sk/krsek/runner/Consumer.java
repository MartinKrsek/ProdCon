package sk.krsek.runner;

import java.util.logging.Logger;
import sk.krsek.buffer.Buffer;
import sk.krsek.dto.Command;
import sk.krsek.dto.ObjectCommand;
import sk.krsek.exception.UnhandledValueException;
import sk.krsek.model.User;
import sk.krsek.repository.RepositoryInterface;
import sk.krsek.repository.UserRepository;

public class Consumer implements Runnable {

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private final Buffer buffer;
  private boolean stop;

  public Consumer(Buffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public void run() {
    while (!stop) {
      Command command = buffer.get();

      if (command != null) {
        logger.info("Consumed " + command.getType());

        switch (command) {
          case ObjectCommand objCommand -> handleObjectCommand(objCommand);
          case Command runnerCommand -> handleRunnerCommand(runnerCommand);
        }
      }
    }
  }

  private void handleObjectCommand(ObjectCommand objCommand) {
    RepositoryInterface repository;
    switch (objCommand.getObject()) {
      case User ignored -> repository = new UserRepository();
      // to support printAll() and deleteAll() commands, in future I do suggest to call printAll and deleteAll
      // methods with target object type
      case null -> repository = new UserRepository();
      default ->
          throw new UnhandledValueException(objCommand.getObject().getClass().getSimpleName(), "Command.ObjectType");
    }

    switch (objCommand.getType()) {
      case ADD -> repository.save(objCommand.getObject());
      case PRINT_ALL -> System.out.println(repository.get());
      case DELETE_ALL -> repository.remove();
      default -> throw new UnhandledValueException(objCommand.getType().name(), "Command.CommandType");
    }
  }

  private void handleRunnerCommand(Command runnerCommand) {
    switch (runnerCommand.getType()) {
      case STOP -> stop = true;
      default -> throw new UnhandledValueException(runnerCommand.getType().name(), "Command.CommandType");
    }
  }
}
