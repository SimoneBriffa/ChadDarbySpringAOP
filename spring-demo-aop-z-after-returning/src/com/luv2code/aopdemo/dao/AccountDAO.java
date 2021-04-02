package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;
	
	
	public List<Account> findAccounts(){
		
		List<Account> myAccounts = new ArrayList<>();
		
		Account temp1 = new Account("John", "Silver");
		Account temp2 = new Account("Josh", "Platinum");
		Account temp3 = new Account("Johnathan", "Gold");
		
		myAccounts.add(temp1); myAccounts.add(temp2); myAccounts.add(temp3);
		
		return myAccounts;
	}
	
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
