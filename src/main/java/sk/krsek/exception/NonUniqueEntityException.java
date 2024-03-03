package sk.krsek.exception;

public class NonUniqueEntityException extends ExceptionAbstraction {

  public NonUniqueEntityException(String message) {
    super(409, message);
  }
}
