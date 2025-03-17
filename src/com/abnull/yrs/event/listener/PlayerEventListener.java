package com.abnull.yrs.event.listener;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.abnull.yrs.stat.PlayerStat;
import com.abnull.yrs.YRSModHelpPlugin;

public class PlayerEventListener implements Listener {

	@EventHandler
	public void onEntityAttack(EntityDamageByEntityEvent event)
	{
		Entity damager = event.getDamager();
		Entity damagee = event.getEntity();
		
		double damage = event.getDamage();
		
		if(damager instanceof CraftPlayer)
		{
			CraftPlayer player = (CraftPlayer)damager;
			PlayerStat stat = YRSModHelpPlugin.player_stat_manager.get_player_stat(player.getEntityId());
			
			float str_strength = YRSModHelpPlugin.stat_config.str_strength;
			float dex_probability = YRSModHelpPlugin.stat_config.dex_probability;
			float dex_critical_multiple = YRSModHelpPlugin.stat_config.dex_critical_multiple;
			
			int str_point = stat.str;
			
			damage += (str_strength * str_point);
			
			double random = Math.random();
			if(dex_probability * str_point > random)
			{
				damage *= dex_critical_multiple;
				player.sendMessage(ChatColor.RED + "Critical!!!");
			}
			
			// ADD ACC Function
			
 		}
		
		if(damagee instanceof CraftPlayer)
		{			
			CraftPlayer player = (CraftPlayer) damagee;
			PlayerStat stat = YRSModHelpPlugin.player_stat_manager.get_player_stat(player.getEntityId());
			
			float def_defence = YRSModHelpPlugin.stat_config.def_defence;
			float agi_probability = YRSModHelpPlugin.stat_config.agi_probability;
			
			int def_point = stat.def;
			int agi_point = stat.agi;
			
			damage *= (1 - (def_defence * def_point));
			
			double random = Math.random();
			if(agi_probability * agi_point > random)
			{
				damage = 0;
				event.setCancelled(true);
				player.sendMessage(ChatColor.YELLOW + "Evasion!!!");
			}
		}
		
		event.setDamage(damage);
	}
}
