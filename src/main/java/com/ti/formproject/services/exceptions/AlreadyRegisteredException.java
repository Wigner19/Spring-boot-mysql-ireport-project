package com.ti.formproject.services.exceptions;

public class AlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadyRegisteredException() {
		super("Already exist");
	}
}
