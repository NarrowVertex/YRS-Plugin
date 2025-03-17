package com.abnull.yrs.stat;

import java.util.HashMap;

import net.minecraft.server.v1_7_R4.EntityPlayer;

public class PlayerStatManager {

	public HashMap<Integer, PlayerStat> player_stat_map = new HashMap<Integer, PlayerStat>();
	
	public PlayerStatManager()
	{
		
	}
	
	public PlayerStat add_player_stat(EntityPlayer player)
	{
		return add_player_stat(player.getId());
	}
	
	public PlayerStat add_player_stat(int player_id)
	{
		PlayerStat player_stat = new PlayerStat();
		player_stat_map.put(player_id, player_stat);
		return player_stat;
	}
	
	public PlayerStat get_player_stat(EntityPlayer player)
	{
		return get_player_stat(player.getId());
	}
	
	public PlayerStat get_player_stat(int player_id)
	{
		return player_stat_map.get(player_id);
	}
}
