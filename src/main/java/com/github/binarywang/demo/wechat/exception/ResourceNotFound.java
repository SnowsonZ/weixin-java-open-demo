package com.example.springboot.exception;

/**
 * Exception used when some resource was not found.
 */

public class ResourceNotFound extends Exception {

	private static final long serialVersionUID = 4351540485172327163L;
 
	public ResourceNotFound() {
		super();
	}

	public ResourceNotFound(String resouce) {
		super("Cannot find " + resouce + " !");
	}
}
