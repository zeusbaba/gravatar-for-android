package com.wareninja.opensource.gravatar4android.common;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GenericException(Throwable cause) {
		super("Gravatar could not be downloaded: " + cause.getMessage(), cause);
	}

	public GenericException(String cause) {
		super("Gravatar exception: " + cause);
	}
}
