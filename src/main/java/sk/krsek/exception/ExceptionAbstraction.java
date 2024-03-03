package sk.krsek.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class ExceptionAbstraction extends RuntimeException {

  /**
   * value representing HTTP status in case this app will be used as a REST API and to simplify error message into
   * simple integer value understandable by the client
   */
  private int status;
  private String message;
}
