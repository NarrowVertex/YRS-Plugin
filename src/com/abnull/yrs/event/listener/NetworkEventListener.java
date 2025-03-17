package com.abnull.yrs.event.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.abnull.yrs.stat.PlayerStat;
import com.abnull.yrs.YRSModHelpPlugin;
import com.abnull.yrs.event.ConnectServerEvent;
import com.abnull.yrs.event.ReceiveServerDataEvent;

public class NetworkEventListener implements Listener{

	@EventHandler
	public void onConnectServer(ConnectServerEvent event)
	{
		YRSModHelpPlugin.debug.print("Sending PN Message With " + "'YRSModHelpPlugin'" + " To Server");
		YRSModHelpPlugin.server_communication_manager.send_message("PN", "YRSModHelpPlugin");
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onReceiveServerData(ReceiveServerDataEvent event)
	{
		String label = event.data_label;
		String contents = event.data_contents;
		
		if(label.equals("SD"))
		{
			YRSModHelpPlugin.debug.print("Set Stat Values From SD Message Sent By The Server. . .");
			
			String[] contents_array = contents.split(",");
			float str_strength = Float.parseFloat(contents_array[0]);
			float def_defence = Float.parseFloat(contents_array[1]);
			float dex_probability = Float.parseFloat(contents_array[2]);
			float dex_critical_multiple = Float.parseFloat(contents_array[3]);
			float acc_accuracy = Float.parseFloat(contents_array[4]);
			float agi_probability = Float.parseFloat(contents_array[5]);
			
			YRSModHelpPlugin.stat_config.str_strength = str_strength;
			YRSModHelpPlugin.stat_config.def_defence = def_defence;
			YRSModHelpPlugin.stat_config.dex_probability = dex_probability;
			YRSModHelpPlugin.stat_config.dex_critical_multiple = dex_critical_multiple;
			YRSModHelpPlugin.stat_config.acc_accuracy = acc_accuracy;
			YRSModHelpPlugin.stat_config.agi_probability = agi_probability;
		}
		else if(label.equals("PSD"))
		{
			String[] contents_array = contents.split(",");
			int player_id = Integer.parseInt(contents_array[0]);
			int str = Integer.parseInt(contents_array[1]);
			int def = Integer.parseInt(contents_array[2]);
			int acc = Integer.parseInt(contents_array[3]);
			int agi = Integer.parseInt(contents_array[4]);
			
			PlayerStat player_stat = YRSModHelpPlugin.player_stat_manager.add_player_stat(player_id);
			player_stat.str = str;
			player_stat.def = def;
			player_stat.acc = acc;
			player_stat.agi = agi;
		}
		else if(label.equals("CS"))
		{
			String[] contents_array = contents.split(",");
			String data_player_name = contents_array[0];
			String data_command = contents_array[1];
			String data_is_level_command = contents_array[2];
			
			if(data_is_level_command.equals("true"))
			{
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "magicspellcast forcecast " + data_player_name + " " + data_command);
			}
			else if(data_is_level_command.equals("false"))
			{
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getPlayer(data_player_name), data_command);
			}
		}
	}
}
