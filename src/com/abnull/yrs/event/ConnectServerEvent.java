package com.abnull.yrs.event;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ConnectServerEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public BufferedReader server_input;
	public BufferedWriter server_output;
	
	public ConnectServerEvent(BufferedReader input, BufferedWriter output)
	{
		server_input = input;
		server_output = output;
	}
	
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
