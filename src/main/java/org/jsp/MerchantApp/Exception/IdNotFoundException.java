package org.jsp.MerchantApp.Exception;

public class IdNotFoundException extends RuntimeException {
	    public String getMessage() {
        	return "Invalid Id";
        }
}
