package com.order.exception;

public class OrderException extends RuntimeException {

	public OrderException(String msg) {
		super(msg);
	}
	public OrderException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
