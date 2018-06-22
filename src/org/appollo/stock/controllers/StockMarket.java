package org.appollo.stock.controllers;

import java.util.List;

import org.appollo.stock.entities.*;
public class StockMarket {
	
	private int current_round=0;
	private List<Player> player_list;
	private int[] market_trends=new int[10];
	private int[] general_trends=new int[10];
	private Sector[] sector_trends=new Sector[4];
	private List<StockEvent> event_list;
	
	public int[] getGeneral_trends() {
		return general_trends;
	}
	public void setGeneral_trends(int[] general_trends) {
		this.general_trends = general_trends;
	}
	public int getCurrent_round() {
		return current_round;
	}
	public void setCurrent_round(int current_round) {
		this.current_round = current_round;
	}
	public List<Player> getPlayer_list() {
		return player_list;
	}
	public void setPlayer_list(List<Player> player_list) {
		this.player_list = player_list;
	}
	public int[] getMarket_trends() {
		return market_trends;
	}
	public void setMarket_trends(int[] market_trends) {
		this.market_trends = market_trends;
	}
	public Sector[] getSector_trends() {
		return sector_trends;
	}
	public void setSector_trends(Sector[] sector_trends) {
		this.sector_trends = sector_trends;
	}
	public List<StockEvent> getEvent_list() {
		return event_list;
	}
	public void setEvent_list(List<StockEvent> event_list) {
		this.event_list = event_list;
	}
	
	public void initMarket() {
		generateMarketTrends();
		generateSectorTrends();
		generateEventTrends();
		checkPlayers();
		getCompanyValues();
		generateGeneralTrends();
	}
	
	private void generateMarketTrends() {
		
	}
	private void generateSectorTrends() {
		
	}
	private void generateEventTrends() {
		
	}
	private void checkPlayers() {
		
	}
	private void getCompanyValues() {
		
	}
	private void generateGeneralTrends() {
		
	}
}
