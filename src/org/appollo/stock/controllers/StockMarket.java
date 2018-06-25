package org.appollo.stock.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.appollo.stock.entities.*;
import org.appollo.stock.support.MarketSectorSupport;
import org.appollo.stock.support.PlayerSupport;
import org.appollo.stock.support.StocksSupport;
public class StockMarket {
	
	private static int current_round=1;
	private static List<Player> players;
	private static int[] market_predictions=new int[10];
	private static int[] general_predictions=new int[10];
	private static List<Sector> sector_predictions=new ArrayList<Sector>();
	private static List<StockEvent> event_predictions=new ArrayList<StockEvent>();
	private static List<StockPredictions> company_predictions=new ArrayList<StockPredictions>();
	public static boolean start=false;
	
	public static int[] getGeneral_trends() {
		return general_predictions;
	}
	public static void setGeneral_trends(int[] generalTrends) {
		general_predictions = generalTrends;
	}
	public static int getCurrent_round() {
		return current_round;
	}
	public static void setCurrent_round(int currentRound) {
		current_round = currentRound;
	}
	public static List<Player> getPlayer_list() {
		return players;
	}
	public static void setPlayer_list(List<Player> playerList) {
		players = playerList;
	}
	public static int[] getMarket_trends() {
		return market_predictions;
	}
	public static void setMarket_trends(int[] marketTrends) {
		market_predictions = marketTrends;
	}
	public static List<Sector> getSector_trends() {
		return sector_predictions;
	}
	public static void setSector_trends(List<Sector> sectorTrends) {
		sector_predictions = sectorTrends;
	}
	public static List<StockEvent> getEvent_list() {
		return event_predictions;
	}
	public static List<StockPredictions> getCompanyTrends(){
		return company_predictions;
	}
	public void setEvent_list(List<StockEvent> eventList) {
		event_predictions = eventList;
	}
	
	public static void initMarket() {
		if(MarketSectorSupport.getAll().isEmpty()) {
			MarketSectorSupport.init();
		}
		if(StocksSupport.getAll().isEmpty()) {
			StocksSupport.init();
		}
		if(market_predictions.length<10) {
			getMarketPredictions();
		}
		if(sector_predictions.isEmpty()) {
			getSectorPredictions();
		}
		if(event_predictions.isEmpty()) {
			getEventPredictions();
		}
		if(general_predictions.length<10) {
			getGeneralPredictions();
		}
		if(company_predictions.isEmpty()) {
			getRoundSharesValue();
		}
//		checkPlayers();
//		getCompanyValues();
	}
	
	
	private static void getMarketPredictions() {
		for(int i=0;i<10;i++){
		       market_predictions[i] = -2 + (int) (Math.random() * ((2 - (-2)) + 1));
		}
	}
	
	private static void getSectorPredictions() {
		List<Sector> sectors=MarketSectorSupport.getAll();
		for(Sector sector:sectors) {
			for(int i=0;i<10;i++) {
				Sector prediction=new Sector(sector.getSector_name());
				int sec = -3 + (int) (Math.random() * ((3 - (-3)) + 1));
				prediction.setSector_Trend(sec);
				sector_predictions.add(prediction);
			}
		}
	}
	
	private static void getEventPredictions() {
		int end_round = 0;
		int start_round=0;
		int round_durations=0;
		String event_type="";
		while(end_round<10){
			if(end_round==0){
				start_round=2;
			}
			else{
				start_round=end_round+1;
			}
			round_durations = (int )(Math.random() * 8 + 1);
			end_round=start_round+round_durations;
			while(end_round>10){
				round_durations = (int )(Math.random() * 8 + 1);
				end_round=start_round+round_durations;               
			}
			//Random Market Component: 
			int value_range=-6 + (int) (Math.random() * ((5 - (-6)) + 1));
			if(value_range<=3 && value_range>=2 )
			    event_type="PROFIT_WARNING";
			if((value_range<=-3 && value_range>=-6)&&(value_range<=-1 && value_range>=-5) ){
				int chk = (int )(Math.random() * 2 + 1);
			    if(chk==1){
			        event_type="TAKE_OVER ";
			    }
			    if(chk==2){
			        event_type="SCANDAL ";
			    }
			}
			if(value_range<=-3 && value_range>=-6){
			    event_type="SCANDAL ";
			}
			if(value_range<=-1 && value_range>=-5){
			     event_type="TAKE_OVER ";
			}
			StockEvent event= new StockEvent(event_type,start_round,end_round,value_range);
			event_predictions.add(event);
		}
	}
	public static void checkPlayers() {
		if(PlayerSupport.getAll().size()<3) {
			while(PlayerSupport.getAll().size()<3) {
				Player p=new Player(genPlayerName());
				PlayerSupport.save(p);
				ArtificialPlayer.newAiPlayer(p);
				ArtificialPlayer.play();
			}
		}
		if(start==false) {
			start=true;
			MarketTimer.goRound();
		}
	}
//	private void getCompanyValues() {
//		
//	}
	private static void getGeneralPredictions() {
		for(int i=0;i<10;i++)
			general_predictions[i] = -3 + (int) (Math.random() * ((3 - (-3)) + 1));
		
	}
	
	private static String genPlayerName() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	public static void getRoundSharesValue() {
		
		List<Stocks> company_list=StocksSupport.getAll();
		for(Stocks stock:company_list) {
			double share_value=0;
			double[] round_values=new double[10];
			for(int i=0;i<10;i++) {
				int curr_round=StockMarket.getCurrent_round()-1;
				if(i==0) {
					share_value=stock.getShare_Vlaue();
				}else {
					share_value=round_values[i-1];
				}
				int sec_trend=0;
				List<Sector> sectors_list=new ArrayList<Sector>();
				for(Sector sector:sector_predictions) {
					if(stock.getCompany_Sector()==sector.getSector_name()) {
						sectors_list.add(sector);
					}
				}
				if(sectors_list.get(i)!=null) {
					sec_trend=sectors_list.get(i).getSector_Trend();
				}
				int evnt_prediction=0;
				for(StockEvent evnt:event_predictions) {
					if(i<=evnt.getEnd_turn() && i>=evnt.getStart_turn()) {
						evnt_prediction=evnt.getEvent_value();
					}
				}
				share_value=share_value+market_predictions[i]+general_predictions[i]+sec_trend+evnt_prediction;
				round_values[i]=share_value;
				if(curr_round==i) {
					stock.setShare_Vlaue(round_values[i]);
					StocksSupport.update(stock);
				}
			}
			StockPredictions comp_prediction=new StockPredictions(stock.getCompany_Name(), round_values);
			company_predictions.add(comp_prediction);
		}
		
	}
}
