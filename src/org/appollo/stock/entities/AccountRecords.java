package org.appollo.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@XmlRootElement(name = "account_transaction")
public class AccountRecords implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String account_holder;
	private String transaction_type;
	private double transaction_amount;
	private String trans_time;
	private double balance_after;
	public AccountRecords() {
		
	}
	public AccountRecords(String account_holder, String transaction_type, double transaction_amount,
			double balance_after) {
		this.account_holder = account_holder;
		this.transaction_type = transaction_type;
		this.transaction_amount = transaction_amount;
		this.balance_after = balance_after;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		this.trans_time=dtf.format(now);
	}
	
	public String getTime() {
		return trans_time;
	}
	@XmlElement
	public void setTime(String time) {
		this.trans_time = time;
	}
	public String getAccount_holder() {
		return account_holder;
	}
	@XmlElement
	public void setAccount_holder(String account_holder) {
		this.account_holder = account_holder;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	@XmlElement
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public double getTransaction_amount() {
		return transaction_amount;
	}
	@XmlElement
	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public double getBalance_after() {
		return balance_after;
	}
	@XmlElement
	public void setBalance_after(double balance_after) {
		this.balance_after = balance_after;
	}

}
