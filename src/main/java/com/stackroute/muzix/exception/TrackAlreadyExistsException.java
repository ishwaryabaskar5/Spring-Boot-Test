package com.stackroute.muzix.exception;


//custom exception TrackAlreadyExistsException
public class TrackAlreadyExistsException extends Exception {
	
	public TrackAlreadyExistsException() {
	}
	
	public TrackAlreadyExistsException(String message) {
		super(message);
	}
}
