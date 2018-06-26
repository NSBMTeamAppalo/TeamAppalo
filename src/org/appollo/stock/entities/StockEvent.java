package org.appollo.stock.entities;

public class StockEvent {
	private String event_type;
	private int start_turn;
	private int end_turn;
	private int event_value;
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public int getStart_turn() {
		return start_turn;
	}
	public void setStart_turn(int start_turn) {
		this.start_turn = start_turn;
	}
	public int getEnd_turn() {
		return end_turn;
	}
	public void setEnd_turn(int end_turn) {
		this.end_turn = end_turn;
	}
	public int getEvent_value() {
		return event_value;
	}
	public void setEvent_value(int event_value) {
		this.event_value = event_value;
	}
	public StockEvent() {
		
	}
	public StockEvent(String event_type, int start_turn, int end_turn, int event_value) {
		this.event_type = event_type;
		this.start_turn = start_turn;
		this.end_turn = end_turn;
		this.event_value = event_value;
	}
	
	
}
