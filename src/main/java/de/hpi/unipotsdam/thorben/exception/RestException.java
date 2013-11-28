package de.hpi.unipotsdam.thorben.exception;

import javax.ws.rs.core.Response.Status;

public class RestException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private static final String DEFAULT_ERROR_MESSAGE = "Unknown server error";

  private Status status;
  
  public RestException(Status status) {
    this(DEFAULT_ERROR_MESSAGE, status);
  }
  
  public RestException(String message, Status status) {
    super(message);
    this.status = status;
  }

  public Status getStatus() {
    return status;
  }
}
