package org.jsp.MerchantApp.Exception;

import org.jsp.MerchantApp.Dto.ResponseStructure;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MerchantBootAppExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ResponseStructure<String>> handelICE(InvalidCredentialsException exception){
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setData("Cannot Find Merchant");
		structure.setMessage(exception.getMessage());
		structure.setStatus_code(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		  
		
	}

}
