package sk.krsek.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import sk.krsek.exception.ConstructorArgumentException;
import sk.krsek.model.User;

public class ObjectCommandTest {

  @Test
  void constructor_addTypeWithoutObject_throws() {
    assertThrows(ConstructorArgumentException.class, () -> new ObjectCommand(CommandType.ADD));
  }

  @Test
  void constructor() {
    User user = new User(1, UUID.randomUUID().toString(), "Martin");
    ObjectCommand objectCommand = new ObjectCommand(CommandType.ADD, user);
    assertEquals(CommandType.ADD, objectCommand.getType());
    assertEquals(user, objectCommand.getObject());
  }

  @Test
  void validate_withAddTypeWithoutObject_throws() {
    assertThrows(ConstructorArgumentException.class,() -> new ObjectCommand(CommandType.ADD).validate());
  }

  @Test
  void validate_withAddTypeAndObject() {
    User user = new User(1, UUID.randomUUID().toString(), "Martin");
    ObjectCommand objectCommand = new ObjectCommand(CommandType.ADD, user);
    assertDoesNotThrow(objectCommand::validate);
  }
}
