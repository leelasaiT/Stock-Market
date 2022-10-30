package com.stockmarket.message;

/**
 * Model for response message
 *
 * @author leelasai
 */
public class ResponseMessage {

  private String message;

  public ResponseMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
