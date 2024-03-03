package sk.krsek.dto;

public enum CommandType {
  STOP, ADD, PRINT_ALL, DELETE_ALL;

  public boolean requiresObject() {
    return this == ADD;
  }

  public boolean supportedOnObjects() {
    return this == ADD || this == PRINT_ALL || this == DELETE_ALL;
  }

  public boolean supportedOnRunners() {
    return this == STOP;
  }

}
