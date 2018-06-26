package org.appollo.stock.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "sector")
public class Sector implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String sector_name;
	private int Sector_Trend;
	
	public String getSector_name() {
		return sector_name;
	}
	@XmlElement
	public void setSector_name(String sector_name) {
		this.sector_name = sector_name;
	}
	public int getSector_Trend() {
		return Sector_Trend;
	}
	@XmlElement
	public void setSector_Trend(int sector_Trend) {
		Sector_Trend = sector_Trend;
	}
	public Sector() {
		
	}
	public Sector(String sector_name) {
		this.sector_name = sector_name;
		this.Sector_Trend=0;
	}
	
	
}
