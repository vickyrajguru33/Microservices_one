package com.user.exceptoin;


public class UserException extends RuntimeException {
	
	public UserException(String msg) {
		super(msg);
	};
	public UserException(String msg, Throwable cause) {
		super(msg,cause);
	}
	
	

}
