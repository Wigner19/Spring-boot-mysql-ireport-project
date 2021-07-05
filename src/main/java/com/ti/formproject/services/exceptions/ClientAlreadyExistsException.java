package com.ti.formproject.services.exceptions;

public class ClientAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClientAlreadyExistsException() {
		super("The client already exsist");
	}
}
