package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		//read spring configuration java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		
		//get bean from spring container
		AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		
		/*In output vedremo che al get e al set non verrà applicato "===========> Performing Api Analytics" */
		Account account = new Account();
		accountDAO.addAccount(account);
		accountDAO.doWork();
		accountDAO.getName();
		accountDAO.getServiceCode();
		
		
		MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		membershipDAO.addSucons();
		
		//close the context
		context.close();
		
	}

}
