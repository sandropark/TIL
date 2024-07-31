package org.sandro.msapattern.exception;

public class UnsupportedStateTransitionException extends RuntimeException {
  public UnsupportedStateTransitionException(Enum state) {
    super("current state: " + state);
  }
}