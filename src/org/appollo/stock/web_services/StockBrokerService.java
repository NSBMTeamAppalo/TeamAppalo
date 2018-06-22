package org.appollo.stock.web_services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.appollo.stock.entities.BankAccount;
import org.appollo.stock.entities.CompanyStocks;
import org.appollo.stock.entities.Player;
import org.appollo.stock.entities.PlayerStocks;
import org.appollo.stock.entities.StockTransactions;
import org.appollo.stock.support.BankAccountSupport;
import org.appollo.stock.support.CompanyStocksSupport;
import org.appollo.stock.support.PlayerSupport;
import org.appollo.stock.support.PlayerStocksSupport;
import org.appollo.stock.support.StockTransactionsSupport;

@Path("/stock-broker")
public class StockBrokerService {
	@GET
    @Produces({ MediaType.APPLICATION_JSON})
    public List<CompanyStocks> getStockPrices() {
        List<CompanyStocks> companies = CompanyStocksSupport.getAll();
        return companies;
    }
	
	@GET
    @Path("/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Player getPlayer(@PathParam("player") String player) {
        return PlayerSupport.get(player);
    }
	
	@GET
    @Path("stock-trans/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<StockTransactions> getTrans(@PathParam("player") String player) {
        return StockTransactionsSupport.getTransactions(player);
    }
	@GET
    @Path("player-stocks/{player}")
    @Produces({ MediaType.APPLICATION_JSON})
    public List<PlayerStocks> getPlayerStocks(@PathParam("player") String player) {
        return PlayerStocksSupport.getStocks(player);
    }
	
	@POST
	@Path("new-player")
    @Produces({ MediaType.APPLICATION_JSON})
    public Player addplayer(Player player) {
		BankAccount bank_account=new BankAccount(player.getPlayer_name());
		BankAccountSupport.save(bank_account);
        return PlayerSupport.save(player);
    }
	
}
