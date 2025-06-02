package com.ldar01.demoemployees.exception;

public class VacationNotFoundException extends RuntimeException {
    public VacationNotFoundException(String message) {
        super(message);
    }

  public VacationNotFoundException() {}

  public VacationNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
