package sk.krsek.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import sk.krsek.exception.ConstructorArgumentException;

@Data
@NoArgsConstructor
public class Command {

  protected CommandType type = CommandType.STOP;

  public Command(CommandType type) {

    if (type == null) {
      throw new ConstructorArgumentException("CommandType cannot be null.");
    }
    this.type = type;
  }

  public void validate() {
    if (!type.supportedOnRunners()) {
      throw new ConstructorArgumentException("CommandType " + type + " is not supported in Command.");
    }
  }


}
