package com.stackroute.muzix.exception;

//custom exception TrackNotFoundException
public class TrackNotFoundException extends Exception {
	
	public TrackNotFoundException() {
	}
	
	public TrackNotFoundException(String message) {
		super(message);
	}
}
