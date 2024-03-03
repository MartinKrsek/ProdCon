package sk.krsek.exception;

public class AppInitializationException extends ExceptionAbstraction {

  public AppInitializationException(String message) {
    super(500, message);
  }
}
