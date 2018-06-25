package org.appollo.stock.starters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.appollo.stock.controllers.StockMarket;

@WebListener
public class AppContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("System Exitted.");	
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		StockMarket.initMarket();
		System.out.println("System Initialized.");
	}
	
}
