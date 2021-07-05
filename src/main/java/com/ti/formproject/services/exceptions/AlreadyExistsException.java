package com.ti.formproject.services.exceptions;

public class AlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadyExistsException() {
		super("Already exists");
	}
}
