package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;
	
	public void addAccount(Account theAccount) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADD AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return false;
	}

	public String getName() {
		System.out.println("GetName");
		return name;
	}

	public void setName(String name) {
		System.out.println("SetName");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println("GetServiceName");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println("SetServiceName");
		this.serviceCode = serviceCode;
	}
	
}
