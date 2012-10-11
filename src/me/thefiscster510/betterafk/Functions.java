package me.thefiscster510.betterafk;

import org.bukkit.ChatColor;
//import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Functions {
	public void afk(Player player, boolean set){
		
		if(set){
			Main.plugin.afk.put(player, set);
			Afk.time.put(player, Main.plugin.getConfig().getInt("Afk.Time") * 20);
			EnvListen.radius.put(player, player.getLocation());
			if(Main.plugin.getConfig().getBoolean("Prefix.Enabled")){
				if(!player.getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefix.AfkPrefix")))){
					player.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefix.AfkPrefix")+player.getDisplayName()));
				}
				
			}
			player.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Afk.IsAfk").replace("%PLAYERNAME%", player.getName()).replace("%PLAYERDISP%", player.getDisplayName())));
		}else{
			Main.plugin.afk.remove(player);
			EnvListen.radius.remove(player);
			player.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Afk.NoLongerAfk").replace("%PLAYERNAME%", player.getName()).replace("%PLAYERDISP%", player.getDisplayName())));
			if(Main.plugin.getConfig().getBoolean("Prefix.Enabled")){
				player.setDisplayName(player.getDisplayName().replace(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefix.AfkPrefix")), ""));
			}
		}
	}
	public String CheckConfig(){
		//FileConfiguration c = Main.plugin.getConfig();
		if(isInt(Main.plugin.getConfig().getString("Afk.Time"))){
			if(Main.plugin.getConfig().getBoolean("Kick.Enabled")){
				if(isInt(Main.plugin.getConfig().getString("Kick.Time"))){
					if(isInt(Main.plugin.getConfig().getString("Afk.Radius"))){
						return "GOOD";
					}else{
						return "ERROR: Error in config! Error at \"Akf.Radius\": "+Main.plugin.getConfig().getString("Akf.Radius")+" not a number";
					}
				}else{
					return "ERROR: Error in config! Error at \"Kick.Time\": "+Main.plugin.getConfig().getString("Kick.Time")+" not a number";
				}
			}else{
				return "GOOD";
			}
		}else{
			return "ERROR: Error in config! Error at \"Afk.Time\": "+Main.plugin.getConfig().getString("Afk.Time")+" not a number";
		}
	}
	
	 public boolean isInt(String input){
		 try {
			    Integer.parseInt(input);
			    return true;
		} catch(NumberFormatException nFE) {
			    return false;
		}
	 }
	 
	 public void kick(Player p){
		 if(Main.plugin.getConfig().getBoolean("Prefix.Enabled")){
				p.setDisplayName(p.getDisplayName().replace(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Prefix.AfkPrefix")), ""));
		 }
		 p.kickPlayer(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Kick.KickReason").replace("%PLAYERNAME%", p.getName()).replace("%PLAYERDISP%", p.getDisplayName())));
		 Main.plugin.afk.remove(p);
		 Afk.time.remove(p);
		 p.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Kick.KickReason").replace("%PLAYERNAME%", p.getName()).replace("%PLAYERDISP%", p.getDisplayName())));
	 }
	 
	 public boolean isAfk(Player p){
		 if(Main.plugin.afk.containsKey(p)){
			 return true;
		 }else{
			 return false;
		 }
	 }
	 
	 public void NoLongerAfk(Player p){
		p.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString("Afk.NoLongerAfk").replace("%PLAYERNAME%", p.getName()).replace("%PLAYERDISP%", p.getDisplayName())));
	 }
	 
     
	 
	 
}
