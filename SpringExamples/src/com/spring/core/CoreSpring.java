package com.spring.core;

public class CoreSpring {
	
	private String message;
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message=message;
	}
	
	public void printHello() {
		System.out.println("Welcome "+message);
	}
	
}
