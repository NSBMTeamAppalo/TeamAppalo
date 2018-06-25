package org.appollo.stock.controllers;

import org.appollo.stock.entities.Sector;

public class SectorPredictions {
	private int round;
	private Sector sector;
	
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
	

}
