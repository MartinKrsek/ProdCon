package sk.krsek.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import sk.krsek.exception.ConstructorArgumentException;

public class CommandTest {

  @Test
  void constructor_withNullType_throws() {
    assertThrows(ConstructorArgumentException.class, () -> new Command(null));
  }

  @Test
  void constructor_empty_shouldSetDefault() {
    Command command = new Command();
    assertEquals(CommandType.STOP, command.getType());
  }

}
