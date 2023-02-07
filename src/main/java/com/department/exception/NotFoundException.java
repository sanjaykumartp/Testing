package com.department.exception;
import java.lang.Object;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = "Not Found aaaaaaaaaaaaaaa")
public class NotFoundException extends RuntimeException {
	

	public NotFoundException(String message) {
		
		super(message);
	}
}
