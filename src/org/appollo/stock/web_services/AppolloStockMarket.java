package org.appollo.stock.web_services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.appollo.stock.controllers.ArtificialPlayer;
import org.appollo.stock.controllers.StockMarket;
import org.appollo.stock.entities.BankAccount;
import org.appollo.stock.entities.Player;
import org.appollo.stock.entities.PlayerStocks;
import org.appollo.stock.entities.StockPredictions;
import org.appollo.stock.entities.Stocks;
import org.appollo.stock.support.BankAccountSupport;
import org.appollo.stock.support.PlayerStocksSupport;
import org.appollo.stock.support.PlayerSupport;
import org.appollo.stock.support.StocksSupport;

@Path("/appollo-market")
public class AppolloStockMarket {
	
	@GET
    @Path("start")
    @Produces({ MediaType.APPLICATION_JSON })
    public String startAppollo() {
		try {
        StockMarket.initMarket();
        return "Success";
		}catch(Exception e) {
        	return e.getMessage();
        }
    }
	
	@GET
    @Path("player-list")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Player> getPlayerList() {
        return PlayerSupport.getAll();
    }
	
	@GET
    @Path("company-list")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Stocks> getCompanyList() {
        return StocksSupport.getAll();
    }
	
	@GET
    @Path("current-round")
    @Produces({ MediaType.APPLICATION_JSON })
    public int getCurrentRound() {
        return StockMarket.getCurrent_round();
    }
	
	
	@GET
    @Path("player-stocks/{player_name}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<PlayerStocks> getPlayerShares(@PathParam("player_name") String player_name) {
        return PlayerStocksSupport.getStocks(player_name);
    }
	
	@GET
    @Path("bank-accounts")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<BankAccount> getBankAccounts() {
        return BankAccountSupport.getAll();
    }
	
	@GET
    @Path("ai-players")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Player> getAIplayers() {
        return ArtificialPlayer.getPlayers();
    }
	@GET
    @Path("stock-predictions")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<StockPredictions> getStockPredictions() {
        return StockMarket.getCompanyTrends();
    }
}
