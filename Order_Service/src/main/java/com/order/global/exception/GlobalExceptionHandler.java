package com.order.global.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.order.exception.OrderException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorResponse> handleOrderException(OrderException e){
		
		ErrorResponse error = new ErrorResponse(
				e.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now()
				);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
				
	}

}
