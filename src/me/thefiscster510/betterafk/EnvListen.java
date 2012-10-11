package me.thefiscster510.betterafk;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class EnvListen implements Listener{
	public Functions functions = new Functions();
	static HashMap<Player, Location> radius = new HashMap<Player, Location>();
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		Afk.time.put(e.getPlayer(), 0);
		if(functions.isAfk(e.getPlayer())){
			functions.afk(e.getPlayer(), false);
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Afk.time.put(e.getPlayer(), 0);
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		Afk.time.remove(e.getPlayer());
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Location l = e.getPlayer().getLocation();
		int radius1 = Main.plugin.getConfig().getInt("Afk.Radius");
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		Location loc2 = null;
		int x2 = 0;
		int y2 = 0;
		int z2 = 0;
		try{
			loc2 = radius.get(e.getPlayer());
			x2 = loc2.getBlockX() + radius1; 
			y2 = loc2.getBlockY() + radius1;
			z2 = loc2.getBlockZ() + radius1;
		}catch(Exception er){
			
		}
		if(loc2 != null){
			if(x >= x2 || y >= y2 || z >= z2){
				Afk.time.put(e.getPlayer(), 0);
				if(functions.isAfk(e.getPlayer())){
					functions.afk(e.getPlayer(), false);
				}
			}
		}
	}
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Afk.time.put(e.getPlayer(), 0);
		if(functions.isAfk(e.getPlayer())){
			functions.afk(e.getPlayer(), false);
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e){
		Afk.time.put(e.getPlayer(), 0);
		if(functions.isAfk(e.getPlayer())){
			functions.afk(e.getPlayer(), false);
		}
	}
	
	@EventHandler
	public void onItemHeldChange(PlayerItemHeldEvent e){
		Afk.time.put(e.getPlayer(), 0);
		if(functions.isAfk(e.getPlayer())){
			functions.afk(e.getPlayer(), false);
		}
	}
	
	@EventHandler
	public void onPlayerBedLeave(PlayerBedLeaveEvent e){
		Afk.time.put(e.getPlayer(), 0);
		if(functions.isAfk(e.getPlayer())){
			functions.afk(e.getPlayer(), false);
		}
	}
	@EventHandler
	public void onPlayerToggleSneak(PlayerToggleSneakEvent e){
		Afk.time.put(e.getPlayer(), 0);
		if(functions.isAfk(e.getPlayer())){
			functions.afk(e.getPlayer(), false);
		}
	}
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e){
		Afk.time.put(e.getPlayer(), 0);
		if(functions.isAfk(e.getPlayer())){
			functions.afk(e.getPlayer(), false);
		}
	}
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e){
		Afk.time.put(e.getPlayer(), 0);
		if(functions.isAfk(e.getPlayer())){
			functions.afk(e.getPlayer(), false);
		}
	}
	
	
}
