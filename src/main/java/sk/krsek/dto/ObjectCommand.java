package sk.krsek.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sk.krsek.exception.ConstructorArgumentException;

@AllArgsConstructor
@Getter
public class ObjectCommand extends Command {

  private Object object;

  public ObjectCommand(CommandType type) {
    super(type);
    validate();
  }

  public ObjectCommand(CommandType type, Object object) {
    super(type);
    this.object = object;
    validate();
  }

  public void validate() {
    if (this.type.requiresObject() && object == null) {
      throw new ConstructorArgumentException("CommandType " + type + " requires an object.");
    }
    if (!this.type.supportedOnObjects()) {
      throw new ConstructorArgumentException("CommandType " + type + " is not supported in ObjectCommand.");
    }
  }
}
