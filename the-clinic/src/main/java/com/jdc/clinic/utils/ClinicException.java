package com.jdc.clinic.utils;

public class ClinicException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ClinicException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClinicException(String message) {
		super(message);
	}

}
