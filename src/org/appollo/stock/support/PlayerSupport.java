package org.appollo.stock.support;


import java.util.ArrayList;
import java.util.List;

import org.appollo.stock.entities.Player; 

public class PlayerSupport {
	private static List<Player> playerList = new ArrayList<Player>();
	public static List<Player> getAll(){
		
		return playerList;
	}
	
	public static Player get(String player) {
		Player p=new Player();
		for(Player plyr:playerList) {
			if(plyr.getPlayer_name().equals(player)) {
				p=plyr;
				break;
			}
		}
		return p;
	}
	
	public static List<Player> save(Player player) {	        
	      playerList.add(player);
	      return playerList;
	}
	
	public static void update(Player player) {
		for(int i=0;i<playerList.size();i++) {
			if(playerList.get(i).getPlayer_name().equals(player.getPlayer_name())) {
				playerList.set(i, player);
			}
			
		}
	}
}
