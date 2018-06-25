package org.appollo.stock.controllers;

import java.util.ArrayList;
import java.util.List;

import org.appollo.stock.entities.AccountRecords;
import org.appollo.stock.entities.BankAccount;
import org.appollo.stock.entities.Player;
import org.appollo.stock.entities.PlayerStocks;
import org.appollo.stock.entities.StockPredictions;
import org.appollo.stock.entities.StockTransactions;
import org.appollo.stock.entities.Stocks;
import org.appollo.stock.support.AccountHistory;
import org.appollo.stock.support.BankAccountSupport;
import org.appollo.stock.support.PlayerStocksSupport;
import org.appollo.stock.support.PlayerSupport;
import org.appollo.stock.support.StockTransactionsHistory;
import org.appollo.stock.support.StocksSupport;

public class ArtificialPlayer {
	private static List<Player> ai_list = new ArrayList<Player>();

	public static List<Player> getPlayers() {
		return ai_list;
	}
	
	public static void newAiPlayer(Player p) {
		ai_list.add(p);
	}

	public static void setPlayers(List<Player> players) {
		ArtificialPlayer.ai_list = players;
	}
	
	public static void buy_shares(Player p) {
		List<StockPredictions> predictions=StockMarket.getCompanyTrends();
		List<Stocks> buyable=new ArrayList<Stocks>();
		List<Stocks> stock_list=StocksSupport.getAll();
		List<PlayerStocks> player_stocks=new ArrayList<PlayerStocks>();
		List<PlayerStocks> shares=PlayerStocksSupport.getAll();
		int current_round=StockMarket.getCurrent_round()-1;
		for(StockPredictions prediction:predictions) {
        	
        	int rem_rounds=10-current_round;
        	int upcoming_rounds=rem_rounds/2;
        	double existing_value=prediction.getRound_values()[current_round];
        	double predicted_value=prediction.getRound_values()[(current_round+upcoming_rounds)];
        	double change_percent=(predicted_value/existing_value)*100;
        	if(change_percent>=110) {
        		for(Stocks stocks:stock_list) {
        			if(stocks.getCompany_Name().equals(prediction.getCompany_name())) {
        				buyable.add(stocks);
        			}
        		}
        	}
        }
		
		for(PlayerStocks stock:shares) {
			if(stock.getPlayer()==p.getPlayer_name()) {
				player_stocks.add(stock);
			}
		}
		for(BankAccount bank_acc:BankAccountSupport.getAll()) {
    		if(bank_acc.getAccountHolder().equals(p.getPlayer_name())) {
    			BankAccount player_acc=bank_acc;
    			double balance=player_acc.getAccountBalance();
    			double allowed_balance=balance*3/5;
    			for(Stocks company:buyable) {
    				if(allowed_balance>0) {
    					int allowed_shares=(int)(allowed_balance/company.getShare_Vlaue());
    					if(allowed_shares>0) {
    						for(StockPredictions prediction:predictions) {
            					if(prediction.getCompany_name().equals(company.getCompany_Name())) {
            						double existing=prediction.getRound_values()[current_round];
            						boolean available=false;
            					for(PlayerStocks stock:player_stocks) {
                					if(stock.getCompany().equals(company.getCompany_Name())) {
                						stock.setStock_Count(stock.getStock_Count()+allowed_shares);
                						stock.setStock_Value(stock.getStock_Value()*existing);
                						PlayerStocksSupport.update(stock);
                        				StockTransactions trns=new StockTransactions(p.getPlayer_name(),stock.getCompany(),allowed_shares,existing*allowed_shares,"BUY");
                        				StockTransactionsHistory.save(trns);
                        				BankAccountSupport.withdraw(bank_acc, (existing*allowed_shares));
                						AccountRecords acc_trns=new AccountRecords(bank_acc.getAccountHolder(), "WITHDRAW", (existing*allowed_shares), (bank_acc.getAccountBalance()-(existing*allowed_shares)));
                						AccountHistory.save(acc_trns);
                						allowed_balance=allowed_balance-(existing*allowed_shares);
                						p.setStock_value(p.getStock_value()+(existing*allowed_shares));
                        				PlayerSupport.update(p);
                						available=true;
                					}
                				}
            					if(available==false) {
            						PlayerStocks player_stock=new PlayerStocks(p.getPlayer_name(),company.getCompany_Name(),allowed_shares,existing*allowed_shares);
            						PlayerStocksSupport.save(player_stock);
            						StockTransactions transaction=new StockTransactions(p.getPlayer_name(),company.getCompany_Name(),allowed_shares,existing*allowed_shares,"BUY");
                    				StockTransactionsHistory.save(transaction);
                    				BankAccountSupport.withdraw(bank_acc, (existing*allowed_shares));
            						AccountRecords account_transaction=new AccountRecords(bank_acc.getAccountHolder(), "WITHDRAW", (existing*allowed_shares), (bank_acc.getAccountBalance()-(existing*allowed_shares)));
            						AccountHistory.save(account_transaction);
            						allowed_balance=allowed_balance-(existing*allowed_shares);
            						p.setStock_value(p.getStock_value()+(existing*allowed_shares));
                    				PlayerSupport.update(p);
            					}
            					}
            				}
    					}
    				}
    			}
    			break;
    		}
    		
    	}
	}
	
	public static void sell_shares(Player player) {
		
		List<StockPredictions> predictions=StockMarket.getCompanyTrends();
		List<PlayerStocks> share_list=PlayerStocksSupport.getStocks(player.getPlayer_name());
		List<PlayerStocks> player_stocks=new ArrayList<PlayerStocks>();
		for(PlayerStocks share:share_list) {
			if(share.getPlayer().equals(player.getPlayer_name())) {
				player_stocks.add(share);
			}
		}
		for(StockPredictions prediction:predictions) {
        	int current_round=StockMarket.getCurrent_round()-1;
        	int remaining_rounds=10-current_round;
        	int upcoming_rounds=remaining_rounds/2;
        	double current_value=prediction.getRound_values()[current_round];
        	double future_value=prediction.getRound_values()[(current_round+upcoming_rounds)];
        	double change_percent=(future_value/current_value)*100;
        	if(change_percent>=10) {
        		for(PlayerStocks stock:player_stocks) {
        			if(stock.getCompany().equals(prediction.getCompany_name())) {
        				int available_shares=stock.getStock_Count();
        				int selling=available_shares*3/4;
        				stock.setStock_Count(stock.getStock_Count()-selling);
        				stock.setStock_Value(stock.getStock_Count()*current_value);
        				PlayerStocksSupport.update(stock);
        				StockTransactions trns=new StockTransactions(player.getPlayer_name(),stock.getCompany(),selling,current_value*selling,"SELL");
        				StockTransactionsHistory.save(trns);
        				player.setStock_value(player.getStock_value()-(current_value*selling));
        				PlayerSupport.update(player);
        				for(BankAccount bank_acc:BankAccountSupport.getAll()) {
        					if(bank_acc.getAccountHolder().equals(player.getPlayer_name())) {
        						BankAccountSupport.deposit(bank_acc, (current_value*selling));
        						AccountRecords acc_trns=new AccountRecords(bank_acc.getAccountHolder(), "DEPOSIT", (current_value*selling), (bank_acc.getAccountBalance()+(current_value*selling)));
        						AccountHistory.save(acc_trns);
        						break;
        					}
        				}
        			}
        		}
        	}
        }
	}
	
	public static void play() {
		for(Player player:ai_list) {
			sell_shares(player);
			buy_shares(player);
		}
	}
}
