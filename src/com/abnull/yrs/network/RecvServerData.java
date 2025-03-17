package com.abnull.yrs.network;

import java.io.BufferedReader;
import java.io.IOException;

import org.bukkit.Bukkit;

import com.abnull.yrs.event.ReceiveServerDataEvent;

public class RecvServerData extends Thread{

	BufferedReader server_input;
	
	public RecvServerData(BufferedReader input)
	{
		server_input = input;
	}
	
	public void run()
	{
		try {
			String string;
			
			while((string = server_input.readLine()) != null)
			{
				if(string.contains(":"))
				{
					String data_label = string.split(":")[0];
					String data_contents = string.split(":")[1];
					
					Bukkit.getPluginManager().callEvent(new ReceiveServerDataEvent(data_label, data_contents));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
