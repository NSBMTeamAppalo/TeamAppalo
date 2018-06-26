package org.appollo.stock.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stock_transactions")
public class StockTransactions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String player;
	private String company;
	private int share_amount;
	private double transaction_amount;
	private String transaction_type;
	private String trans_time;
	public String getPlayer() {
		return player;
	}
	
	public String getTrans_time() {
		return trans_time;
	}
	@XmlElement
	public void setTrans_time(String trans_time) {
		this.trans_time = trans_time;
	}

	@XmlElement
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getCompany() {
		return company;
	}
	@XmlElement
	public void setCompany(String company) {
		this.company = company;
	}
	public int getShare_amount() {
		return share_amount;
	}
	@XmlElement
	public void setShare_amount(int share_amount) {
		this.share_amount = share_amount;
	}
	public double getTransaction_amount() {
		return transaction_amount;
	}
	@XmlElement
	public void setTransaction_amount(double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	@XmlElement
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public StockTransactions(String player, String company, int share_amount, double transaction_amount,
			String transaction_type) {
		this.player = player;
		this.company = company;
		this.share_amount = share_amount;
		this.transaction_amount = transaction_amount;
		this.transaction_type = transaction_type;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		this.trans_time=dtf.format(now);
	}
	public StockTransactions() {
		
	}
	
}
