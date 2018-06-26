package org.appollo.stock.entities;

import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "company_trend")
public class StockPredictions {
	private String company_name;
	private double[] round_values=new double[10];
	public String getCompany_name() {
		return company_name;
	}
	@XmlElement
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public double[] getRound_values() {
		return round_values;
	}
	@XmlElement
	public void setRound_values(double[] round_values) {
		this.round_values = round_values;
	}
	public StockPredictions() {
		
	}
	public StockPredictions(String company_name, double[] round_values) {
		this.company_name = company_name;
		this.round_values = round_values;
	}
	
}
