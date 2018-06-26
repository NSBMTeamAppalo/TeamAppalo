package org.appollo.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "player_stocks")
public class PlayerStocks implements Serializable {
	private static final long serialVersionUID = 1L;
	private String player;
	private String Company;
	private int Stock_Count;
	private double Stock_Value;
	
	public String getPlayer() {
		return player;
	}
	@XmlElement
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getCompany() {
		return Company;
	}
	@XmlElement
	public void setCompany(String company) {
		Company = company;
	}
	public int getStock_Count() {
		return Stock_Count;
	}
	@XmlElement
	public void setStock_Count(int stock_Count) {
		Stock_Count = stock_Count;
	}
	public double getStock_Value() {
		return Stock_Value;
	}
	@XmlElement
	public void setStock_Value(double stock_Value) {
		Stock_Value = stock_Value;
	}
	public PlayerStocks() {
		
	}
	public PlayerStocks(String player, String company, int stock_Count, double stock_Value) {
		this.player = player;
		this.Company = company;
		this.Stock_Count = stock_Count;
		this.Stock_Value = stock_Value;
	}
}
