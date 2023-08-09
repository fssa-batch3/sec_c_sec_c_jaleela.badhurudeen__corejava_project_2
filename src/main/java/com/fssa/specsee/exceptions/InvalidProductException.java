package com.fssa.specsee.exceptions;

public class InvalidProductException extends Exception{

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs an InvalidEmployeeException with the specified error message.
	 * 
	 * @param msg The error message describing the reason for the exception.
	 */
	public InvalidProductException(String msg) {
		super(msg);
	}

	/**
	 * Constructs an InvalidEmployeeException with the specified cause.
	 * 
	 * @param te The underlying cause of the exception.
	 */
	public InvalidProductException(Throwable te) {
		super(te);
	}

	/**
	 * Constructs an InvalidEmployeeException with the specified error message and
	 * cause.
	 * 
	 * @param msg The error message describing the reason for the exception.
	 * @param te  The underlying cause of the exception.
	 */
	public InvalidProductException(String msg, Throwable te) {
		super(msg, te);
	}
}
