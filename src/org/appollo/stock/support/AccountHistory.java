package org.appollo.stock.support;

import java.util.ArrayList;
import java.util.List;

import org.appollo.stock.entities.AccountRecords;
public class AccountHistory {
	private static List<AccountRecords> account_transactions=new ArrayList<AccountRecords>();
	public static List<AccountRecords> getAll(){
		return account_transactions;
	}
	
	public static void save(AccountRecords account_transaction) {
		account_transactions.add(account_transaction);
	}
	
	public static List<AccountRecords> getTransactions(String account_holder){
		List<AccountRecords> transactions = new ArrayList<AccountRecords>();
		for(AccountRecords transaction:account_transactions) {
			if(transaction.getAccount_holder().equals(account_holder)) {
				transactions.add(transaction);
			}
		}
		return transactions;
	}
}
