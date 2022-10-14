package com.example.erc_demo.exception;

public class ServerResponseException extends RuntimeException {

  public ServerResponseException(String message) {
    super(message);
  }
}
