package org.appollo.stock.support;

import java.util.ArrayList;
import java.util.List;

import org.appollo.stock.entities.BankAccount;
public class BankAccountSupport {
	private static List<BankAccount> bank_accounts=new ArrayList<BankAccount>();
	public static List<BankAccount> getAll(){
		return bank_accounts;
	}
	
	public static BankAccount get(String account_holder) {
		BankAccount bankAccount=new BankAccount();
		for(int i=0;i<bank_accounts.size();i++) {
			if(bank_accounts.get(i).getAccountHolder().equals(account_holder)) {
				return bank_accounts.get(i);
			}
		}
		return bankAccount;
	}
	
	public static List<BankAccount> withdraw(BankAccount bank_account,double trans_amount) {
		for(int i=0;i<bank_accounts.size();i++) {
			if(bank_accounts.get(i).getAccountHolder().equals(bank_account.getAccountHolder())) {
				bank_account.setAccountBalance(bank_account.getAccountBalance()-trans_amount);
				bank_accounts.set(i, bank_account);
				System.out.println("WITHDRAW");
				break;
			}
		}
		return bank_accounts;
	}
	
	public static List<BankAccount> deposit(BankAccount bank_account,double trans_amount) {
		for(int i=0;i<bank_accounts.size();i++) {
			if(bank_accounts.get(i).getAccountHolder().equals(bank_account.getAccountHolder())) {
				bank_account.setAccountBalance(bank_account.getAccountBalance()+trans_amount);
				bank_accounts.set(i, bank_account);
				System.out.println("DEPOSIT");
				break;
			}
		}
		return bank_accounts;
	}
	
	public static List<BankAccount> save(BankAccount bank_account) {
		boolean found=false;
		for(BankAccount account:bank_accounts) {
			if(account.getAccountHolder().equals(bank_account.getAccountHolder())) {
				found=true;
			}
		}
		if(!found) {
			bank_accounts.add(bank_account);
		}
	    return bank_accounts;
	}
}
