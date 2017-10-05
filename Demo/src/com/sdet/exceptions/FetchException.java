package com.sdet.exceptions;

@SuppressWarnings("serial")
public class FetchException extends Exception {

	public FetchException() {
		
	}

	public FetchException(String arg0) {
		super(arg0);

	}

	public FetchException(Throwable arg0) {
		super(arg0);

	}

	public FetchException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public FetchException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}
