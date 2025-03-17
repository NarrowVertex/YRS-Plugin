package com.abnull.yrs;

import org.bukkit.plugin.java.JavaPlugin;

import com.abnull.yrs.stat.PlayerStatManager;
import com.abnull.yrs.config.StatConfig;
import com.abnull.yrs.event.listener.NetworkEventListener;
import com.abnull.yrs.event.listener.PlayerEventListener;
import com.abnull.yrs.log.Debug;
import com.abnull.yrs.network.ServerCommunicationManager;

public class YRSModHelpPlugin extends JavaPlugin {
	
	public static Debug debug;
	
	public static ServerCommunicationManager server_communication_manager;
	public static PlayerStatManager player_stat_manager;
	
	public static StatConfig stat_config;
	
	@Override
	public void onEnable()
	{
		debug = new Debug("YRSModHelpPlugin");
		
		debug.print("YRSModHelpPlugin Is Enabled. . !");
		
		debug.print("Initializating Event Handlers. . .");
		getServer().getPluginManager().registerEvents(new NetworkEventListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerEventListener(), this);
		
		debug.print("Initializating Managers. . .");
		player_stat_manager = new PlayerStatManager();
		
		debug.print("Initializating Configs. . .");
		stat_config = new StatConfig();
		
		debug.print("Connect to Server[Port:" + 25566 + "]. . .");
		server_communication_manager = new ServerCommunicationManager(25566);
	}
	
	@Override
	public void onDisable()
	{
		debug.print("YRSModHelpPlugin Is Disabled. . !");
	}
}
