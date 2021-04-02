package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundWithLoggerDemoApp {
	
	//Con questo approccio, l'eccezione viene gestita prima che arrivi al main
	
	private static Logger logger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

	public static void main(String[] args) {
		
		//read spring configuration java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		
		//get bean from spring container
		TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		logger.info("\nMain Program: AroundDemoApp");
		
		logger.info("Calling getFortune");
		
		boolean tripWire = true;
		String data = fortuneService.getFortune(tripWire);
		
		logger.info("\nMy fortune is: " + data);
		
		logger.info("Finished");
		
		//close the context
		context.close();
		
	}

}
