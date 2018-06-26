package org.appollo.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

import org.appollo.stock.support.BankAccountSupport;

@XmlRootElement(name = "player")
public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String player_name;
	private double stock_value;
	private int current_round;
	private BankAccount bank_account;
	
	public int getCurrent_round() {
		return current_round;
	}

	@XmlElement
	public void setCurrent_round(int current_round) {
		this.current_round = current_round;
	}
	
	
	public BankAccount getBank_account() {
		return bank_account;
	}
	public void setBank_account(BankAccount bank_account) {
		this.bank_account = bank_account;
		BankAccountSupport.save(this.bank_account);
	}

	public Player() {
		
	}
	
	public Player(String player_name) {
		this.player_name = player_name;
		this.stock_value=0;
		this.current_round=1;
		this.bank_account=new BankAccount(player_name);
		BankAccountSupport.save(this.bank_account);
	}

	public Player(String player_name, double stock_value) {
		this.player_name = player_name;
		this.stock_value = stock_value;
	}
	public String getPlayer_name() {
		return player_name;
	}
	@XmlElement
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public double getStock_value() {
		return stock_value;
	}
	@XmlElement
	public void setStock_value(double stock_value) {
		this.stock_value = stock_value;
	}
}
