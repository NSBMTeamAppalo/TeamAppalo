package org.appollo.stock.controllers;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.appollo.stock.entities.*;
import org.appollo.stock.support.PlayerStocksSupport;
import org.appollo.stock.support.PlayerSupport;
import org.appollo.stock.support.StocksSupport;
public class MarketTimer {
	static int current_time;
    static Timer timer;
    public static void goRound() {
    	int delay = 1000;
        int period = 1000;
        timer = new Timer();
        current_time = 120;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                countDown();
            }
        }, delay, period);
    }
    private static int countDown() {
        if (current_time == 1) {
            timer.cancel();
            current_time=0;
            if(StockMarket.getCurrent_round()<10) {
            	StockMarket.setCurrent_round(StockMarket.getCurrent_round()+1);
            	ArtificialPlayer.play();
            	List<StockPredictions> stock_predictions=StockMarket.getCompanyTrends();
            	for(Stocks company_stock:StocksSupport.getAll()) {
            		for(StockPredictions prediction:stock_predictions) {
            			if(company_stock.getCompany_Name().equals(prediction.getCompany_name())){
            				company_stock.setShare_Vlaue(prediction.getRound_values()[StockMarket.getCurrent_round()-1]);
            				StocksSupport.update(company_stock);
            				
            				for(PlayerStocks stock:PlayerStocksSupport.getAll()) {
                    			if(company_stock.getCompany_Name().equals(stock.getCompany())) {
                    				stock.setStock_Value(stock.getStock_Count()*prediction.getRound_values()[StockMarket.getCurrent_round()-1]);
                    				PlayerStocksSupport.update(stock);
                    			}
                    		}
            			}
            		}
            		
            	}
            	for(Player player:PlayerSupport.getAll()) {
            		List<PlayerStocks> player_shares=PlayerStocksSupport.getStocks(player.getPlayer_name());
            		double total=0;
            		for(PlayerStocks stock:player_shares) {
            			total=total+stock.getStock_Value();
            		}
            		player.setStock_value(total);
            		PlayerSupport.update(player);
            	}
            	goRound();
            }
            return current_time;
        }
        else if (current_time==0){
            return current_time;
        }
        else{
        return --current_time;
        }
    }
}
