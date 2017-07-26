package com.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("Context.xml");
		CoreSpring coreObj = (CoreSpring) context.getBean("corebean");
		coreObj.printHello();
	}
	
}
