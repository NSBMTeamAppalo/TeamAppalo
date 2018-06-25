package org.appollo.stock.web_services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.appollo.stock.controllers.StockMarket;
import org.appollo.stock.entities.AccountRecords;
import org.appollo.stock.entities.BankAccount;
import org.appollo.stock.entities.Player;
import org.appollo.stock.entities.PlayerStocks;
import org.appollo.stock.entities.StockTransactions;
import org.appollo.stock.entities.Stocks;
import org.appollo.stock.support.AccountHistory;
import org.appollo.stock.support.BankAccountSupport;
import org.appollo.stock.support.PlayerStocksSupport;
import org.appollo.stock.support.PlayerSupport;
import org.appollo.stock.support.StockTransactionsHistory;
import org.appollo.stock.support.StocksSupport;

@Path("/stock-broker")
public class StockBroker {
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Stocks> getStockPrices() {
        List<Stocks> stocks = StocksSupport.getAll();
        return stocks;
    }
	
	@GET
    @Path("player/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
    public Player getPlayer(@PathParam("player") String player) {
        return PlayerSupport.get(player);
    }
	@GET
    @Path("bank-account/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
    public BankAccount bankAccount(@PathParam("player") String player) {
        return BankAccountSupport.get(player);
    }
	
	@GET
    @Path("stock-transactions/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<StockTransactions> getTransaction(@PathParam("player") String player) {
        return StockTransactionsHistory.getTransactions(player);
    }
	@GET
    @Path("account-transactions/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<AccountRecords> getAccTransactions(@PathParam("player") String player) {
        return AccountHistory.getTransactions(player);
    }
	@GET
    @Path("player-shares/{player}")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<PlayerStocks> getPlayerShares(@PathParam("player") String player) {
        return PlayerStocksSupport.getStocks(player);
    }
	@GET
    @Path("players")
    @Produces({ MediaType.APPLICATION_JSON })
    public List<Player> getPlayers() {
        return PlayerSupport.getAll();
    }
	@GET
    @Path("stock-count/{company_name}/{player_name}")
    @Produces({ MediaType.APPLICATION_JSON })
    public double getStockCount(@PathParam("company_name") String company_name,@PathParam("player_name") String player_name) {
        return PlayerStocksSupport.getStockCount(company_name, player_name);
    }
	
	@GET
	@Path("new-player/{player_name}")
    @Produces({ MediaType.APPLICATION_JSON })
    public boolean newPlayer(@PathParam("player_name") String player_name) {
		Player player=new Player(player_name);
		boolean not_available=true;
		for(Player p:PlayerSupport.getAll()) {
			if(p.getPlayer_name().equals(player.getPlayer_name())) {
				not_available=false;
			}
		}
		if(not_available) {
			BankAccount bank_account=new BankAccount(player.getPlayer_name());
			BankAccountSupport.save(bank_account);
			PlayerSupport.save(player);
			StockMarket.checkPlayers();
			return not_available;
		}else {
			return not_available;
		}
        
    }
	
	@GET
    @Path("sell/{player}/{company}/{stocks}")
    @Produces({ MediaType.APPLICATION_JSON })
    public boolean sellShares(@PathParam("player") String player,@PathParam("company") String company,@PathParam("stocks") int stocks) {
		if(stocks>0) {
			List<PlayerStocks> stocks_list=PlayerStocksSupport.getStocks(player);
			Player plyr=PlayerSupport.get(player);
			for(Stocks cmpny:StocksSupport.getAll()) {
				if(cmpny.getCompany_Name().equals(company)) {
					for(PlayerStocks ps:stocks_list) {
						if(ps.getCompany().equals(cmpny.getCompany_Name())) {
							ps.setStock_Count(ps.getStock_Count()-stocks);
							ps.setStock_Value(ps.getStock_Count()*cmpny.getShare_Vlaue());
	        				PlayerStocksSupport.update(ps);
	        				StockTransactions transaction=new StockTransactions(plyr.getPlayer_name(),ps.getCompany(),stocks,cmpny.getShare_Vlaue()*stocks,"SELL");
	        				StockTransactionsHistory.save(transaction);
	        				plyr.setStock_value(plyr.getStock_value()-(cmpny.getShare_Vlaue()*stocks));
	        				PlayerSupport.update(plyr);
	        				for(BankAccount bank_account:BankAccountSupport.getAll()) {
	        					if(bank_account.getAccountHolder().equals(plyr.getPlayer_name())) {
	        						AccountRecords account_transaction=new AccountRecords(bank_account.getAccountHolder(), "DEPOSIT", (cmpny.getShare_Vlaue()*stocks), (bank_account.getAccountBalance()+(cmpny.getShare_Vlaue()*stocks)));
	        						AccountHistory.save(account_transaction);
	        						BankAccountSupport.deposit(bank_account, (cmpny.getShare_Vlaue()*stocks));
	        						break;
	        					}
	        				}
						}
					}
				}
			}
		}
        return true;
    }
	
	@GET
    @Path("buy/{player}/{company}/{stocks}")
    @Produces({ MediaType.APPLICATION_JSON })
    public boolean buyshares(@PathParam("player") String player,@PathParam("company") String company,@PathParam("stocks") int stocks) {
		List<PlayerStocks> stocks_list=PlayerStocksSupport.getStocks(player);
		Player plyr=PlayerSupport.get(player);
		boolean found=false;
		for(Stocks cmpny:StocksSupport.getAll()) {
			if(cmpny.getCompany_Name().equals(company)) {
				for(PlayerStocks ps:stocks_list) {
					if(ps.getCompany().equals(cmpny.getCompany_Name())) {
						found=true;
        				for(BankAccount bank_account:BankAccountSupport.getAll()) {
        					if(bank_account.getAccountHolder().equals(plyr.getPlayer_name())) {
        						if(bank_account.getAccountBalance()>=(cmpny.getShare_Vlaue()*stocks)) {
        							ps.setStock_Count(ps.getStock_Count()+stocks);
        							ps.setStock_Value(ps.getStock_Count()*cmpny.getShare_Vlaue());
        	        				PlayerStocksSupport.update(ps);
        	        				StockTransactions transaction=new StockTransactions(plyr.getPlayer_name(),ps.getCompany(),stocks,cmpny.getShare_Vlaue()*stocks,"BUY");
        	        				StockTransactionsHistory.save(transaction);
        	        				plyr.setStock_value(plyr.getStock_value()+(cmpny.getShare_Vlaue()*stocks));
        	        				PlayerSupport.update(plyr);
        	        				cmpny.setNo_of_Stocks(cmpny.getNo_of_Stocks()+stocks);
        	        				StocksSupport.update(cmpny);
        							AccountRecords account_transaction=new AccountRecords(bank_account.getAccountHolder(), "WITHDRAW", (cmpny.getShare_Vlaue()*stocks), (bank_account.getAccountBalance()-(cmpny.getShare_Vlaue()*stocks)));
            						AccountHistory.save(account_transaction);
            						BankAccountSupport.withdraw(bank_account, (cmpny.getShare_Vlaue()*stocks));
        						}
        						break;
        					}
        				}
					}
				}
				if(found==false) {
    				for(BankAccount bank_account:BankAccountSupport.getAll()) {
    					if(bank_account.getAccountHolder().equals(plyr.getPlayer_name())) {
    						if(bank_account.getAccountBalance()>=(cmpny.getShare_Vlaue()*stocks)) {
    							PlayerStocks player_stock=new PlayerStocks(plyr.getPlayer_name(),cmpny.getCompany_Name(),stocks,cmpny.getShare_Vlaue()*stocks);
    							PlayerStocksSupport.save(player_stock);
    							StockTransactions transaction=new StockTransactions(plyr.getPlayer_name(),cmpny.getCompany_Name(),stocks,cmpny.getShare_Vlaue()*stocks,"BUY");
    		    				StockTransactionsHistory.save(transaction);
    		    				plyr.setStock_value(plyr.getStock_value()+(cmpny.getShare_Vlaue()*stocks));
    		    				PlayerSupport.update(plyr);
    		    				StocksSupport.update(cmpny);
    							AccountRecords account_transaction=new AccountRecords(bank_account.getAccountHolder(), "WITHDRAW", (cmpny.getShare_Vlaue()*stocks), (bank_account.getAccountBalance()-(cmpny.getShare_Vlaue()*stocks)));
        						AccountHistory.save(account_transaction);
        						BankAccountSupport.withdraw(bank_account, (cmpny.getShare_Vlaue()*stocks));
    						}
    						break;
    					}
    				}
    				
				}
			}
		}
        return true;
    }
	
}
