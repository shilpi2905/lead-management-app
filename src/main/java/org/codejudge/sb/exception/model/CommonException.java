package org.codejudge.sb.exception.model;

import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Error error;
	private HttpStatus statusCode;

	public CommonException(Error error, HttpStatus statusCode) {
		super();
		this.error = error;
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}



	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
