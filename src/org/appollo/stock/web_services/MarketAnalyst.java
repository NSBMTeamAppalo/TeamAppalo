package org.appollo.stock.web_services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.appollo.stock.controllers.StockMarket;
import org.appollo.stock.entities.AnalystSuggestions;
import org.appollo.stock.entities.StockPredictions;

@Path("/market-analyst")
public class MarketAnalyst {
	@GET
	@Path("recommendations")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<AnalystSuggestions> getRecommendation() {
        List<StockPredictions> predictions=StockMarket.getCompanyTrends();
        List<AnalystSuggestions> recommendations=new ArrayList<AnalystSuggestions>();
        for(StockPredictions prediction:predictions) {
        	int current_round=StockMarket.getCurrent_round()-1;
        	int remaining_rounds=10-current_round;
        	int upcoming_rounds=remaining_rounds/2;
        	double current_value=prediction.getRound_values()[current_round];
        	double future_value=prediction.getRound_values()[(current_round+upcoming_rounds)];
        	double change_percent=(future_value/current_value)*100;
        	if(change_percent>=110) {
        		AnalystSuggestions recommendation=new AnalystSuggestions(prediction.getCompany_name(), "BUY");
        		recommendations.add(recommendation);
        	}
        	else if(change_percent>=10 && change_percent<100){
        		AnalystSuggestions recommendation=new AnalystSuggestions(prediction.getCompany_name(), "SELL");
        		recommendations.add(recommendation);
        	}
        }
        return recommendations;
    }
	
}
