package me.thefiscster510.betterafk;

import java.util.HashMap;
import java.util.logging.Logger;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	public static Main plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	public EnvListen pListener = new EnvListen();
	public Functions functions = new Functions();
	HashMap<Player, Boolean> afk = new HashMap<Player, Boolean>();
	@Override
	 public void onEnable() {
		 plugin = this;
		 
		 final Afk w =  new Afk(); 
		 Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, w , 1, 1);
		
		 
		 PluginManager pm = getServer().getPluginManager();
		 pm.registerEvents(this.pListener, this);
		 PluginDescriptionFile pdffile = this.getDescription();
		 this.getConfig().options().copyDefaults(true);
		 this.saveConfig();
		 this.logger.info("["+pdffile.getName()+"] Checking Config..");
		 if(!functions.CheckConfig().equalsIgnoreCase("GOOD")){
			 this.logger.info("["+pdffile.getName()+"] "+functions.CheckConfig());
			 this.getPluginLoader().disablePlugin(this);
		 }else{
			 this.logger.info("["+pdffile.getName()+"] Config Okay!");
			 this.logger.info("["+pdffile.getName()+"] " + pdffile.getName() + " V" + pdffile.getVersion() + " has been enabled!");
		 }
		
	 }
	 @Override
	 public void onDisable(){
		 PluginDescriptionFile pdffile = this.getDescription();
		 Bukkit.getScheduler().cancelAllTasks();
		 this.logger.info("["+pdffile.getName()+"] " + pdffile.getName() + " V" + pdffile.getVersion() + " has been disabled!");
		//Remove any AFK tags
		 for(Player p : getServer().getOnlinePlayers()){
			 p.setDisplayName(p.getDisplayName().replace(getConfig().getString("Prefix.AfkPrefix"), ""));
		 }
	 }
	 public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		 if(commandLabel.equalsIgnoreCase("afk")){
			 
			 if(sender instanceof Player){
			 
				if(args.length == 1){
					if(((Player) sender).hasPermission("simpleafk.afk.other")){
						for(Player p : getServer().getOnlinePlayers()){
							if(p.getName().contains(args[0]) || p.getDisplayName().contains(args[0])){
								if(functions.isAfk(p)){
								 	((Player) sender).sendMessage(ChatColor.RED + p.getDisplayName() + " is already set to AFK.");
							 	}else{
							 		if(!p.hasPermission("simpleafk.exempt")){
							 			functions.afk(p, true);
							 			((Player) sender).sendMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.GREEN + " set to AFK");
							 		}else{
							 			((Player) sender).sendMessage(ChatColor.YELLOW + p.getDisplayName() + ChatColor.RED + "Is exempt from SimpleAFK");
							 		}
							 	}
							}
						}
					}else{
						((Player) sender).sendMessage(ChatColor.RED + "You don't have permission to do this.");
					}
				}else if(args.length > 1){
					((Player) sender).sendMessage(ChatColor.RED + "Usage: /afk [player]");
				}else{
					if(!((Player) sender).hasPermission("simpleafk.exempt")){
						if(((Player) sender).hasPermission("simpleafk.afk.self")){
							if(functions.isAfk((Player) sender)){
								((Player) sender).sendMessage(ChatColor.RED + "You are already set to AFK.");
							}else{
								functions.afk(((Player) sender), true);
							}
						}else{
							((Player) sender).sendMessage(ChatColor.RED + "You don't have permission to do this.");
						}
					}
				}
			 
				 	
			 }else{
				 this.logger.info("This command must be used in game.");
			 }
		 }
		 return true;
	 }
}
