package me.thefiscster510.betterafk;

import java.util.HashMap;


import org.bukkit.entity.Player;

public class Afk implements Runnable{
	
	static HashMap<Player, Integer> time = new HashMap<Player, Integer>();
	public Functions functions = new Functions();
	@Override
	public void run() {
		for(Player p : time.keySet()){
			if(!p.hasPermission("simpleafk.exempt") || p.isOp()){
				time.put(p, time.get(p) + 1);
				try{
				if(time.get(p) == Main.plugin.getConfig().getInt("Afk.Time") * 20){
					functions.afk(p, true);
				}else if(time.get(p) == (Main.plugin.getConfig().getInt("Kick.Time") + Main.plugin.getConfig().getInt("Afk.Time")) * 20){
					if(Main.plugin.getConfig().getBoolean("Kick.Enabled") && Main.plugin.getConfig().getBoolean("Afk.Enabled")){
						functions.kick(p);
					
					}
				}
				}catch (Exception e){
					Main.plugin.logger.info("AfkAfter Error: Not a number");
				}
			}
		}
	}
}
