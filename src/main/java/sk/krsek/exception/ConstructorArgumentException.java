package sk.krsek.exception;

public class ConstructorArgumentException extends ExceptionAbstraction {

  public ConstructorArgumentException(String message) {
    super(500, message);
  }
}
