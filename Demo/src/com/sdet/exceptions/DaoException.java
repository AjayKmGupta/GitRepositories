package com.sdet.exceptions;

@SuppressWarnings("serial")
public class DaoException extends Exception {

	public DaoException() {

	}

	public DaoException(String arg0) {
		super(arg0);

	}

	public DaoException(Throwable arg0) {
		super(arg0);

	}

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public DaoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}
