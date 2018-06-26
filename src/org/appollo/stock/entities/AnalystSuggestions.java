package org.appollo.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "recommendation") 
public class AnalystSuggestions implements Serializable {
	private static final long serialVersionUID = 1L;
	private String company_name;
	private String recommend;
	
	public String getCompany_name() {
		return company_name;
	}
	@XmlElement
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getRecommend() {
		return recommend;
	}
	@XmlElement
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public AnalystSuggestions() {
		
	}
	public AnalystSuggestions(String company_name, String recommend) {
		this.company_name = company_name;
		this.recommend = recommend;
	}
	
	
}
