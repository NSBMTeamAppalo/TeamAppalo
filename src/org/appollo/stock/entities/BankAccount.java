package org.appollo.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "bank_account") 
public class BankAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private String AccountHolder;
	private double AccountBalance;
	
	public BankAccount(String accountHolder) {
		AccountHolder = accountHolder;
		AccountBalance = 1000;
	}
	
	public BankAccount() {
		AccountBalance=1000;
	}


	public static BankAccount getAccount(String name) {
		return new BankAccount();
	}
	
	public String getAccountHolder() {
		return AccountHolder;
	}
	@XmlElement
	public void setAccountHolder(String accountHolder) {
		AccountHolder = accountHolder;
	}
	public double getAccountBalance() {
		return AccountBalance;
	}
	@XmlElement
	public void setAccountBalance(double accountBalance) {
		AccountBalance = accountBalance;
	}
}
