package com.ikkisan.halive.core;

@SuppressWarnings("serial")
public class WebPageException extends Exception{

	public WebPageException() {
		super();
	}

	public WebPageException(Exception expt) {
		super(expt);
	}

	public WebPageException(String mess) {
		super(mess);
	}

	public WebPageException(String mess,Exception ext) {
		super(ext);
	}
}
