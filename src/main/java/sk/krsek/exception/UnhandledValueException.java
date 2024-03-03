package sk.krsek.exception;

public class UnhandledValueException extends ExceptionAbstraction {

  public UnhandledValueException(String value, String field) {
    super(500, "Unhandled value: " + value + " in field: " + field + ".");
  }
}
