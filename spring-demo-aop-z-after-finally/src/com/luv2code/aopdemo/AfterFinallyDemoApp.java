package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
		
		//read spring configuration java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		
		//get bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		try {
			boolean tripWire = true;
			List<Account> accounts = accountDAO.findAccounts(tripWire);
			System.out.println(accounts);
		}catch(Exception exc) {
			System.out.println("Main program: exception catched --> " + exc.getMessage());
		}
		
		//close the context
		context.close();
		
	}

}
