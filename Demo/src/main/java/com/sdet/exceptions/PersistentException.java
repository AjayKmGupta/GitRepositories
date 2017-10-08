package com.sdet.exceptions;

@SuppressWarnings("serial")
public class PersistentException extends Exception {

	public PersistentException() {

	}

	public PersistentException(String arg0) {
		super(arg0);

	}

	public PersistentException(Throwable arg0) {
		super(arg0);

	}

	public PersistentException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public PersistentException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}
