package org.appollo.stock.support;
import java.util.ArrayList;
import java.util.List;

import org.appollo.stock.entities.StockEvent;
public class MarketEventsSupport {
	private static List<StockEvent> event_list=new ArrayList<StockEvent>();
	public static List<StockEvent> getAll(){
		return event_list;
	}
	public static void save(StockEvent event) {
		event_list.add(event);
	}
}
