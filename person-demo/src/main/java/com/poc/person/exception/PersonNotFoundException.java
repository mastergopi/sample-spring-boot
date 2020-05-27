package com.poc.person.exception;

public class PersonNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersonNotFoundException(String message) {
		super(message);
	}

}
