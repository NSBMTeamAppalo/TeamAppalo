package org.appollo.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "company_stocks")
public class Stocks implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String Company_Name;
	private int No_of_Stocks;
	private double Share_Value;
	private String Company_Sector;
	private int Random_Trend;
	
	public int getRandom_Trend() {
		return Random_Trend;
	}
	public void setRandom_Trend(int random_Trend) {
		Random_Trend = random_Trend;
	}
	public String getCompany_Name() {
		return Company_Name;
	}
	@XmlElement
	public void setCompany_Name(String company_Name) {
		Company_Name = company_Name;
	}
	public int getNo_of_Stocks() {
		return No_of_Stocks;
	}
	@XmlElement
	public void setNo_of_Stocks(int no_of_Stocks) {
		No_of_Stocks = no_of_Stocks;
	}
	public double getShare_Vlaue() {
		return Share_Value;
	}
	@XmlElement
	public void setShare_Vlaue(double share_Vlaue) {
		Share_Value = share_Vlaue;
	}
	
	public Stocks(String company_Name, int no_of_Stocks, double share_Vlaue, String company_Sector) {
		Company_Name = company_Name;
		No_of_Stocks = no_of_Stocks;
		Share_Value = share_Vlaue;
		Company_Sector = company_Sector;
	}
	public Stocks() {
		
	}
	public String getCompany_Sector() {
		return Company_Sector;
	}
	@XmlElement
	public void setCompany_Sector(String company_Sector) {
		Company_Sector = company_Sector;
	}
	
	
}
