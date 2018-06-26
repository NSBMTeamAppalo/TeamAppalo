package org.appollo.stock.support;

import java.util.ArrayList;
import java.util.List;

import org.appollo.stock.entities.StockTransactions;

public class StockTransactionsHistory {
	private static List<StockTransactions> account_transactions=new ArrayList<StockTransactions>();
	public static List<StockTransactions> getAll(){
		return account_transactions;
	}
	
	public static void save(StockTransactions account_transaction) {
		account_transactions.add(account_transaction);
	}
	
	public static List<StockTransactions> getTransactions(String player_name){
		List<StockTransactions> transactions = new ArrayList<StockTransactions>();
		for(StockTransactions transaction:account_transactions) {
			if(transaction.getPlayer().equals(player_name)) {
				transactions.add(transaction);
			}
		}
		return transactions;
	}
}
